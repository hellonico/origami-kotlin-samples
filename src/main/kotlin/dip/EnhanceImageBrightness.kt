package dip

import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgcodecs.Imgcodecs.*
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    var alpha = 2.0
    var beta = 50.0
    val source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    val destination = Mat(source.rows(), source.cols(), source.type())
    source.convertTo(destination, -1, alpha, beta)
    imwrite("out/brightWithAlpha2Beta50.jpg", destination)
}