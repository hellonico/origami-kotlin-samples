package sample

import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc.*
import org.scijava.nativelib.NativeLoader
import origami.Origami
import origami.Origami.*
import java.io.IOException

object ImagePyramid {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        init()

        var source = imread("data/dip/digital_image_processing.jpg")

        val destination1 = Mat(source.rows() * 2, source.cols() * 2, source.type())
        pyrUp(source, destination1, Size((source.cols() * 2).toDouble(), (source.rows() * 2).toDouble()))
        imwrite("pyrUp.jpg", destination1)

        val destination = Mat(source.rows() / 2, source.cols() / 2, source.type())
        pyrDown(source, destination, Size((source.cols() / 2).toDouble(), (source.rows() / 2).toDouble()))
        imwrite("pyrDown.jpg", destination)

    }
}
