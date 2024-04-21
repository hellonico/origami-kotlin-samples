package tanaka79

import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.medianBlur
import origami.Origami


fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得
    val dst = Mat()
    medianBlur(im, dst, 19)
    imwrite("out/tanaka_median.jpg", dst) // 出力画像の保存
}