package dip

import kotlin.Throws
import java.io.IOException
import kotlin.jvm.JvmStatic
import origami.Origami
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.core.Core
import org.opencv.core.Core.*
import org.opencv.core.Rect
import org.opencv.imgcodecs.Imgcodecs.*

fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    val waterMark = imread("data/dip/watermark.jpg", IMREAD_COLOR)
    val ROI = Rect(20, 20, waterMark.cols(), waterMark.rows())
    addWeighted(source.submat(ROI), 0.8, waterMark, 0.2, 1.0, source.submat(ROI))
    imwrite("out/watermarkedROI.jpg", source)
}