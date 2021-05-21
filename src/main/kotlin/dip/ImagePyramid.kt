package dip

import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc.pyrDown
import org.opencv.imgproc.Imgproc.pyrUp
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    var source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    val destination1 = Mat(source.rows() * 2, source.cols() * 2, source.type())
    pyrUp(source, destination1, Size((source.cols() * 2).toDouble(), (source.rows() * 2).toDouble()))
    imwrite("pyrUp.jpg", destination1)
    source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    val destination = Mat(source.rows() / 2, source.cols() / 2, source.type())
    pyrDown(source, destination, Size((source.cols() / 2).toDouble(), (source.rows() / 2).toDouble()))
    imwrite("pyrDown.jpg", destination)
}