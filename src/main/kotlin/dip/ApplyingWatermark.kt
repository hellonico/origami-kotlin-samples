package dip

import kotlin.Throws
import java.io.IOException
import kotlin.jvm.JvmStatic
import origami.Origami
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc.*


fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    putText(
        source, "dip.hellonico.info", Point((source.rows() / 2).toDouble(), (source.cols() / 2).toDouble()),
        FONT_ITALIC, 1.0, Scalar(255.0)
    )
    imwrite("out/watermarked.jpg", source)
}
