package tutorialpoint.blur

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
    medianBlur(src, dst, 15)
    imwrite("out/blur.jpg", dst)
}