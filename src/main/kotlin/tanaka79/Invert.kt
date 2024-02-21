package tanaka79

import org.opencv.core.Core
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得
    Core.bitwise_not(im, im) // 色反転(Not演算)
    imwrite("out/tanaka_invert.jpg", im) // 出力画像の保存
}