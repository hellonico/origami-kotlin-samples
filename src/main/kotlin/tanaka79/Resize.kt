package tanaka79

import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.resize
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得
    val im2 = Mat()
    val im3 = Mat()
    val sz = im.size()
    resize(im, im2, Size(sz.width * 2, sz.height * 2)) // 2倍拡大
    resize(im, im3, Size(sz.width * 0.5, sz.height * 0.5)) // 1/2倍に縮小
    imwrite("out/tanaka_resize2.jpg", im2) // 出力画像の保存
    imwrite("out/tanaka_resize05.jpg", im3) // 出力画像の保存
}