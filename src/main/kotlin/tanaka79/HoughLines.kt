package tanaka79

import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.*
import origami.Origami


fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得
    val gray = Mat(im.rows(), im.cols(), CvType.CV_8SC1)
    cvtColor(im, gray, COLOR_RGB2GRAY) // グレースケール変換
    Canny(gray, gray, 70.0, 110.0) // 輪郭線検出
    val lines = Mat()
    // 古典的ハフ変換で直線検出
    HoughLines(gray, lines, 1.0, 2 * Math.PI / 180, 20)
    // 検出した直線上を赤線で塗る
    for (i in 0 until lines.cols()) {
        val data = lines[0, i]
        val rho = data[0]
        val theta = data[1]
        val cosTheta = Math.cos(theta)
        val sinTheta = Math.sin(theta)
        val x0 = cosTheta * rho
        val y0 = sinTheta * rho
        val pt1 = Point(x0 + 10000 * -sinTheta, y0 + 10000 * cosTheta)
        val pt2 = Point(x0 - 10000 * -sinTheta, y0 - 10000 * cosTheta)
        line(im, pt1, pt2, Scalar(0.0, 0.0, 200.0), 3)
    }
    imwrite("out/tanaka_houghlines.jpg", im) // 出力画像の保存
}