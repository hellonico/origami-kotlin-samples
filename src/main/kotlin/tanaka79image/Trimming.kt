package tanaka79image

import org.opencv.core.Mat
import org.opencv.core.Rect
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgcodecs.Imgcodecs.*
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    // 入力画像の取得
    val im = imread("data/lupin3.jpeg")
    val roi = Rect(280, 60, 120, 100)
    val im2 = Mat(im, roi)
    // 結果を保存
    imwrite("out/tanaka_trimming.png", im2)
}