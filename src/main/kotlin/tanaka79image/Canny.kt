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
    cvtColor(im, gray, COLOR_RGB2GRAY) // グレースケール変換
    Canny(gray, gray, 400.0, 500.0, 5, true) // Cannyアルゴリズムで輪郭検出
    imwrite("out/tanaka_canny.jpg", gray) // エッジ画像の出力
}
