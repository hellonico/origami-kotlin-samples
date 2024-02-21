package tanaka79

import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.Laplacian
import origami.Origami


fun main(args: Array<String>) {
    Origami.init()
    val gray = imread("data/lupin3.jpeg", 0)
    Laplacian(gray, gray, gray.depth())
    imwrite("out/tanaka_laplacian.jpg", gray)
}
