package dip

import kotlin.Throws
import java.io.IOException
import kotlin.jvm.JvmStatic
import origami.Origami
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.core.Core
import org.opencv.core.Rect

fun main(args: Array<String>) {
    Origami.init()
    val source = Imgcodecs.imread("data/dip/digital_image_processing.jpg", Imgcodecs.IMREAD_COLOR)
    val waterMark = Imgcodecs.imread("data/dip/watermark.jpg", Imgcodecs.IMREAD_COLOR)
    val ROI = Rect(20, 20, waterMark.cols(), waterMark.rows())
    Core.addWeighted(source.submat(ROI), 0.8, waterMark, 0.2, 1.0, source.submat(ROI))
    Imgcodecs.imwrite("watermarkedROI.jpg", source)
}