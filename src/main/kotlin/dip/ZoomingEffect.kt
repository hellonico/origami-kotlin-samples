package dip

import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc.INTER_LINEAR
import org.opencv.imgproc.Imgproc.resize
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/grayscale.jpg", IMREAD_GRAYSCALE)
    val zoomingFactor = 3
    val destination = Mat(source.rows(), source.cols(), source.type())
    resize(
        source,
        destination,
        destination.size(),
        zoomingFactor.toDouble(),
        zoomingFactor.toDouble(),
        INTER_LINEAR
    )
    imwrite("zoomed2.jpg", destination)
}