package dip

import org.opencv.core.Core.addWeighted
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc.GaussianBlur
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    val destination = Mat(source.rows(), source.cols(), source.type())
    GaussianBlur(source, destination, Size(1.0, 1.0), 10.0)
    addWeighted(source, 1.5, destination, -0.5, 0.0, destination)
    imwrite("out/sharp.jpg", destination)
}