package dip

import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc.*
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    val destination = Mat(source.rows(), source.cols(), source.type())
    val erosion_size = 5
    val element = getStructuringElement(
        MORPH_RECT,
        Size((2 * erosion_size + 1).toDouble(), (2 * erosion_size + 1).toDouble())
    )
    erode(source, destination, element)
    imwrite("erosion.jpg", destination)
    val dilation_size = 5
    val element1 = getStructuringElement(
        MORPH_RECT,
        Size((2 * dilation_size + 1).toDouble(), (2 * dilation_size + 1).toDouble())
    )
    dilate(source, destination, element1)
    imwrite("out/dilation.jpg", destination)
}