package tanaka79

import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.Canny
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val gray = imread("data/lupin3.jpeg", 0) // 入力画像の取得
    Canny(gray, gray, 100.0, 200.0, 3, true)
    imwrite("out/tanaka_canny.jpg", gray) // 画像データをJPG形式で保存
}