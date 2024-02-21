package tanaka79

import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.Sobel
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val gray = imread("data/lupin3.jpeg", 0)
    Sobel(gray, gray, gray.depth(), 2, 2)
    imwrite("out/tanaka_sobel.jpg", gray)
}
