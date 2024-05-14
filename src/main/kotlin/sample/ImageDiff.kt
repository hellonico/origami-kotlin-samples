package sample

import org.opencv.core.Core.*
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.highgui.HighGui.*
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc.*
import org.scijava.nativelib.NativeLoader.*
import origami.Origami


object ImageDiff {

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        Origami.init()

        val img1 = imread("data/lena.jpg")
        val img2 = imread("data/lena.jpg")

        // create a new image object to store image difference
        val diffImg = Mat()

        // compute the difference between the images
        absdiff(img1, img2, diffImg)

        // now convert it to grey and threshold it
        val grey = Mat()
        cvtColor(diffImg, grey, COLOR_BGR2GRAY)

        adaptiveThreshold(grey, diffImg, 255.0, ADAPTIVE_THRESH_MEAN_C, THRESH_BINARY_INV, 15,
                7.0)

        // now clean it up using some morphological operations
        val ksize = Size(5.0, 5.0)
        val kernel = getStructuringElement(MORPH_ELLIPSE, ksize)
        morphologyEx(diffImg, diffImg, MORPH_CLOSE, kernel)

        imshow("Image 1", img1)
        imshow("Image 2", img2)
        imshow("Diff", diffImg)

    }
}
