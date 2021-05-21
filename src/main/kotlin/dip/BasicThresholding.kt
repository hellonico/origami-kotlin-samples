package dip

import kotlin.Throws
import java.io.IOException
import kotlin.jvm.JvmStatic
import origami.Origami
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Imgproc.*

fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    val destination = Mat(source.rows(), source.cols(), source.type())
    threshold(source, destination, 127.0, 255.0, THRESH_TOZERO)
    imwrite("ThreshZero.jpg", destination)
    threshold(source, destination, 127.0, 255.0, THRESH_TOZERO_INV)
    imwrite("ThreshZeroInv.jpg", destination)
    threshold(source, destination, 127.0, 255.0, THRESH_BINARY)
    imwrite("ThreshBinary.jpg", destination)
    threshold(source, destination, 127.0, 255.0, THRESH_BINARY_INV)
    imwrite("ThreshBinaryInv.jpg", destination)
}