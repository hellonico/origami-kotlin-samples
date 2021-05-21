package tanaka79

import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.blur
import origami.Origami


fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得
    val dst = Mat()
    blur(im, dst, Size(5.0, 5.0))
    imwrite("out/tanaka_blur.jpg", dst) // 出力画像の保存
}