package tanaka79image

import org.opencv.core.Core.bitwise_not
import org.opencv.core.Core.inRange
import org.opencv.core.Mat
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.COLOR_BGR2HSV
import org.opencv.imgproc.Imgproc.cvtColor
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得
    val hsv = Mat()
    val mask = Mat()
    val im2 = Mat()
    cvtColor(im, hsv, COLOR_BGR2HSV) // HSV色空間に変換
    inRange(hsv, Scalar(100.0, 10.0, 0.0), Scalar(140.0, 255.0, 255.0), mask) // 緑色領域のマスク作成
    im.copyTo(im2, mask) // マスクを 用いて入力画像から緑色領域を抽出
    imwrite("out/tanakahsvmask.jpg", im2) // 画像の出力
    bitwise_not(mask, mask)
    val im3 = Mat()
    im.copyTo(im3, mask) // マスクを 用いて入力画像から緑色領域を抽出
    imwrite("out/tanakahsv.jpg", im3) // 画像の出力
}