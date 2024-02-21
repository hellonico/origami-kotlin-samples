package tanaka79image

import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.INTER_NEAREST
import org.opencv.imgproc.Imgproc.resize
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得
    resize(im, im, Size(), 0.1, 0.1, INTER_NEAREST) // 画像サイズを1/10倍
    resize(im, im, Size(), 10.0, 10.0, INTER_NEAREST) // 画像サイズを10倍
    imwrite("out/tanaka_mosaic.jpg", im) // 画像の出力
}