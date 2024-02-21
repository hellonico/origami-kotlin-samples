package tutorialpoint.blur

import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.GaussianBlur
import origami.Origami


fun main(args: Array<String>) {
    Origami.init()
    val src = imread("data/marcel2019.jpg")
    val dst = Mat()
    GaussianBlur(src, dst, Size(45.0, 45.0), 0.0)
    imwrite("out/gaussianmarcel.jpg", dst)
}
