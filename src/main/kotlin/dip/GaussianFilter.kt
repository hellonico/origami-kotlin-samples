package dip

import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc.GaussianBlur
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    val destination = Mat(source.rows(), source.cols(), source.type())
    GaussianBlur(source, destination, Size(11.0, 11.0), 0.0)
    imwrite("gaussianblur1.jpg", destination)
    GaussianBlur(source, destination, Size(45.0, 45.0), 0.0)
    imwrite("gaussianblur45.jpg", destination)
}