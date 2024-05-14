package sample

import java.io.File
import java.io.IOException
import java.util.ArrayList

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.DMatch
import org.opencv.core.KeyPoint
import org.opencv.core.Mat
import org.opencv.core.MatOfDMatch
import org.opencv.core.MatOfKeyPoint
import org.opencv.core.Scalar
import org.opencv.features2d.AKAZE
import org.opencv.features2d.DescriptorMatcher
import org.opencv.features2d.Features2d
import org.opencv.highgui.HighGui
import org.opencv.imgcodecs.Imgcodecs
import org.scijava.nativelib.NativeLoader
import org.w3c.dom.Document
import org.xml.sax.SAXException

internal class AKAZEMatch {

    fun run(args: Array<String>) {
        // ! [load]
        val filename1 = if (args.size > 2) args[0] else "data/graf1.png"
        val filename2 = if (args.size > 2) args[1] else "data/graf3.png"
        val filename3 = if (args.size > 2) args[2] else "data/H1to3p.xml"
        val img1 = Imgcodecs.imread(filename1, Imgcodecs.IMREAD_GRAYSCALE)
        val img2 = Imgcodecs.imread(filename2, Imgcodecs.IMREAD_GRAYSCALE)
        if (img1.empty() || img2.empty()) {
            System.err.println("Cannot read images!")
            System.exit(0)
        }

        val file = File(filename3)
        val documentBuilderFactory = DocumentBuilderFactory.newInstance()
        val documentBuilder: DocumentBuilder
        val document: Document
        val homography = Mat(3, 3, CvType.CV_64F)
        val homographyData = DoubleArray((homography.total() * homography.channels()).toInt())
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder()
            document = documentBuilder.parse(file)
            val homographyStr = document.getElementsByTagName("data").item(0).textContent
            val splited = homographyStr.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            var idx = 0
            for (s in splited) {
                if (!s.isEmpty()) {
                    homographyData[idx] = java.lang.Double.parseDouble(s)
                    idx++
                }
            }
        } catch (e: ParserConfigurationException) {
            e.printStackTrace()
            System.exit(0)
        } catch (e: SAXException) {
            e.printStackTrace()
            System.exit(0)
        } catch (e: IOException) {
            e.printStackTrace()
            System.exit(0)
        }

        homography.put(0, 0, *homographyData)
        // ! [load]

        // ! [AKAZE]
        val akaze = AKAZE.create()
        val kpts1 = MatOfKeyPoint()
        val kpts2 = MatOfKeyPoint()
        val desc1 = Mat()
        val desc2 = Mat()
        akaze.detectAndCompute(img1, Mat(), kpts1, desc1)
        akaze.detectAndCompute(img2, Mat(), kpts2, desc2)
        // ! [AKAZE]

        // ! [2-nn matching]
        val matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING)
        val knnMatches = ArrayList<MatOfDMatch>()
        matcher.knnMatch(desc1, desc2, knnMatches, 2)
        // ! [2-nn matching]

        // ! [ratio test filtering]
        val ratioThreshold = 0.8f // Nearest neighbor matching ratio
        val listOfMatched1 = ArrayList<KeyPoint>()
        val listOfMatched2 = ArrayList<KeyPoint>()
        val listOfKeypoints1 = kpts1.toList()
        val listOfKeypoints2 = kpts2.toList()
        for (i in knnMatches.indices) {
            val matches = knnMatches[i].toArray()
            val dist1 = matches[0].distance
            val dist2 = matches[1].distance
            if (dist1 < ratioThreshold * dist2) {
                listOfMatched1.add(listOfKeypoints1[matches[0].queryIdx])
                listOfMatched2.add(listOfKeypoints2[matches[0].trainIdx])
            }
        }
        // ! [ratio test filtering]

        // ! [homography check]
        val inlierThreshold = 2.5 // Distance threshold to identify inliers with homography check
        val listOfInliers1 = ArrayList<KeyPoint>()
        val listOfInliers2 = ArrayList<KeyPoint>()
        val listOfGoodMatches = ArrayList<DMatch>()
        for (i in listOfMatched1.indices) {
            val col = Mat(3, 1, CvType.CV_64F)
            val colData = DoubleArray((col.total() * col.channels()).toInt())
            colData[0] = listOfMatched1[i].pt.x
            colData[1] = listOfMatched1[i].pt.y
            colData[2] = 1.0
            col.put(0, 0, *colData)

            val colRes = Mat()
            Core.gemm(homography, col, 1.0, Mat(), 0.0, colRes)
            colRes.get(0, 0, colData)
            Core.multiply(colRes, Scalar(1.0 / colData[2]), col)
            col.get(0, 0, colData)

            val dist = Math.sqrt(Math.pow(colData[0] - listOfMatched2[i].pt.x, 2.0) + Math.pow(colData[1] - listOfMatched2[i].pt.y, 2.0))

            if (dist < inlierThreshold) {
                listOfGoodMatches.add(DMatch(listOfInliers1.size, listOfInliers2.size, 0f))
                listOfInliers1.add(listOfMatched1[i])
                listOfInliers2.add(listOfMatched2[i])
            }
        }
        // ! [homography check]

        // ! [draw final matches]
        val res = Mat()
        val inliers1 = MatOfKeyPoint(*listOfInliers1.toTypedArray())
        val inliers2 = MatOfKeyPoint(*listOfInliers2.toTypedArray())
        val goodMatches = MatOfDMatch(*listOfGoodMatches.toTypedArray())
        Features2d.drawMatches(img1, inliers1, img2, inliers2, goodMatches, res)
        Imgcodecs.imwrite("akaze_result.png", res)

        val inlierRatio = listOfInliers1.size / listOfMatched1.size.toDouble()
        println("A-KAZE Matching Results")
        println("*******************************")
        println("# Keypoints 1:                        \t" + listOfKeypoints1.size)
        println("# Keypoints 2:                        \t" + listOfKeypoints2.size)
        println("# Matches:                            \t" + listOfMatched1.size)
        println("# Inliers:                            \t" + listOfInliers1.size)
        println("# Inliers Ratio:                      \t$inlierRatio")

        HighGui.imshow("result", res)
        HighGui.waitKey()
        // ! [draw final matches]

        System.exit(0)
    }

    companion object {
        init {
            try {
                // Load the native OpenCV library
                NativeLoader.loadLibrary(Core.NATIVE_LIBRARY_NAME)
            } catch (e: Exception) {

            }

        }
    }
}

object AKAZEMatchDemo {
    @JvmStatic
    fun main(args: Array<String>) {

        AKAZEMatch().run(args)
    }
}
