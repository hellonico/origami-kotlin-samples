package tanaka79image

import kotlin.jvm.JvmStatic
import origami.Origami
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Imgproc.*

fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得

    // RGBカラー画像の画素値を取得
    var data = DoubleArray(3)
    data = im[100, 200]
    println("Blue：" + data[0])
    println("Green：" + data[1])
    println("Red：" + data[2])

    // グレースケール画像の画素値を取得
    val gray = Mat()
    cvtColor(im, gray, COLOR_RGB2GRAY) // 画像のグレースケール変換
    var data2 = DoubleArray(1)
    data2 = gray[100, 200]
    println("Gray：" + data2[0])
}