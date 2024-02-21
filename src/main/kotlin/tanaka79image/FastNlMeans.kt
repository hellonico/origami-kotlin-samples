package tanaka79image

import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.photo.Photo
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得
    Photo.fastNlMeansDenoising(im, im)
    imwrite("out/tanaka_denoisingz.jpg", im) // 画像の出力
}
