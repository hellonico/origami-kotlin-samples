package dip

import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc.equalizeHist
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/grayscale.jpg", IMREAD_GRAYSCALE)
    val destination = Mat(source.rows(), source.cols(), source.type())
    equalizeHist(source, destination)
    imwrite("out/contrast.jpg", destination)
}