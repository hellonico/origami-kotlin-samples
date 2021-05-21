package stackoverflow

import org.opencv.core.Core.CMP_EQ
import org.opencv.core.Core.compare
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.Rect
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.GC_INIT_WITH_RECT
import org.opencv.imgproc.Imgproc.grabCut
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val mat = imread("data/marcel2019.jpg")
    val result = extractFace(mat, 300, 1200, 300, 900)
    imwrite("out/grabcut.jpg", result)
}

fun extractFace(image: Mat, xOne: Int, xTwo: Int, yOne: Int, yTwo: Int): Mat {
    val rectangle = Rect(xOne, yOne, xTwo, yTwo)
    val result = Mat()
    val bgdModel = Mat()
    val fgdModel = Mat()
    val source = Mat(1, 1, CvType.CV_8U, Scalar(3.0))
    val iteration:Int = 1
    grabCut(image, result, rectangle, bgdModel, fgdModel, iteration, GC_INIT_WITH_RECT)
    compare(result, source, result, CMP_EQ)
    val foreground = Mat(image.size(), CvType.CV_8UC3, Scalar(255.0, 255.0, 255.0))
    image.copyTo(foreground, result)
    return foreground
}