package tutorialpoint.filtering

import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val src = Imgcodecs.imread("data/marcel2019.jpg")
    val dst = Mat()
    val size = Size(45.0, 45.0)
    val point = Point(-1.0, -1.0)
    Imgproc.boxFilter(src, dst, 50, size, point, true, Core.BORDER_DEFAULT)
    Imgcodecs.imwrite("boxFilter.jpg", dst)
}
