package tanaka79image

import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.*
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得
    val gray = Mat()
    cvtColor(im, gray, COLOR_RGB2GRAY) // 画像のグレースケール変換
    equalizeHist(gray, gray) // グレースケール画像のヒストグラムを平坦化
    imwrite("out/tanaka_hist.jpg", gray) // 画像の出力
}