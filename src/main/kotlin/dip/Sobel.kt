package dip

import org.opencv.core.Core
import org.opencv.core.CvType.CV_32F
import org.opencv.core.Mat
import org.opencv.highgui.HighGui.imshow
import org.opencv.highgui.HighGui.waitKey
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc.filter2D
import origami.Origami.init

fun main(args: Array<String>) {
    init()
    val kernelSize = 3
    val source = imread("data/dip/grayscale.jpg", IMREAD_GRAYSCALE)
    val destination = Mat(source.rows(), source.cols(), source.type())
    val kernel: Mat = object : Mat(kernelSize, kernelSize, CV_32F) {
        init {
            put(0, 0, -1.0)
            put(0, 1, 0.0)
            put(0, 2, 1.0)

            put(1, 0, - 2.0)
            put(1, 1, 0.0)
            put(1, 2, 2.0)

            put(2, 0, -1.0)
            put(2, 1, 0.0)
            put(2, 2, 1.0)
        }
    }

    filter2D(source, destination, -1, kernel)
    imwrite("sobel.jpg", destination)
}