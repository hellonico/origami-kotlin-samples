package tutorialpoint.filtering

import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Imgproc.*
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val src = imread("data/marcel2019.jpg")
    val dst = Mat()
    bilateralFilter(src, dst, 15, 80.0, 80.0, Core.BORDER_DEFAULT)
    imwrite("out/bilateral.jpg", dst)
}
