package sample

import org.opencv.core.*
import org.opencv.highgui.HighGui.*
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc.*
import org.opencv.ml.Ml
import org.opencv.ml.SVM
import org.scijava.nativelib.NativeLoader
import origami.Origami
import origami.Origami.*


object IntroductionToSVMDemo {

    @JvmStatic
    fun main(args: Array<String>) {
        init()
        // Set up training data
        //! [setup1]
        val labels = intArrayOf(1, -1, -1, -1)
        val trainingData = floatArrayOf(501f, 10f, 255f, 10f, 501f, 255f, 10f, 501f)
        //! [setup1]
        //! [setup2]
        val trainingDataMat = Mat(4, 2, CvType.CV_32FC1)
        trainingDataMat.put(0, 0, trainingData)
        val labelsMat = Mat(4, 1, CvType.CV_32SC1)
        labelsMat.put(0, 0, labels)
        //! [setup2]

        // Train the SVM
        //! [init]
        val svm = SVM.create()
        svm.type = SVM.C_SVC
        svm.setKernel(SVM.LINEAR)
        svm.termCriteria = TermCriteria(TermCriteria.MAX_ITER, 100, 1e-6)
        //! [init]
        //! [train]
        svm.train(trainingDataMat, Ml.ROW_SAMPLE, labelsMat)
        //! [train]

        // Data for visual representation
        val width = 512
        val height = 512
        val image = Mat.zeros(height, width, CvType.CV_8UC3)

        // Show the decision regions given by the SVM
        //! [show]
        val imageData = ByteArray((image.total() * image.channels()).toInt())
        val sampleMat = Mat(1, 2, CvType.CV_32F)
        val sampleMatData = FloatArray((sampleMat.total() * sampleMat.channels()).toInt())
        for (i in 0 until image.rows()) {
            for (j in 0 until image.cols()) {
                sampleMatData[0] = j.toFloat()
                sampleMatData[1] = i.toFloat()
                sampleMat.put(0, 0, sampleMatData)
                val response = svm.predict(sampleMat)

                if (response == 1f) {
                    imageData[(i * image.cols() + j) * image.channels()] = 0
                    imageData[(i * image.cols() + j) * image.channels() + 1] = 255.toByte()
                    imageData[(i * image.cols() + j) * image.channels() + 2] = 0
                } else if (response == -1f) {
                    imageData[(i * image.cols() + j) * image.channels()] = 255.toByte()
                    imageData[(i * image.cols() + j) * image.channels() + 1] = 0
                    imageData[(i * image.cols() + j) * image.channels() + 2] = 0
                }
            }
        }
        image.put(0, 0, imageData)
        //! [show]

        // Show the training data
        //! [show_data]
        var thickness = -1
        val lineType = LINE_8
        circle(image, Point(501.0, 10.0), 5, Scalar(0.0, 0.0, 0.0), thickness, lineType, 0)
        circle(image, Point(255.0, 10.0), 5, Scalar(255.0, 255.0, 255.0), thickness, lineType, 0)
        circle(image, Point(501.0, 255.0), 5, Scalar(255.0, 255.0, 255.0), thickness, lineType, 0)
        circle(image, Point(10.0, 501.0), 5, Scalar(255.0, 255.0, 255.0), thickness, lineType, 0)
        //! [show_data]

        // Show support vectors
        //! [show_vectors]
        thickness = 2
        val sv = svm.uncompressedSupportVectors
        val svData = FloatArray((sv.total() * sv.channels()).toInt())
        sv.get(0, 0, svData)
        for (i in 0 until sv.rows()) {
            circle(image, Point(svData[i * sv.cols()].toDouble(), svData[i * sv.cols() + 1].toDouble()), 6,
                    Scalar(128.0, 128.0, 128.0), thickness, lineType, 0)
        }
        //! [show_vectors]

        imwrite("result.png", image) // save the image

        imshow("SVM Simple Example", image) // show it to the user
        waitKey()
    }
}
