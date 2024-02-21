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
    //Imgproc.Canny(gray, gray, 80, 100);										// 輪郭線検出
    val circles = Mat()
    // ハフ変換で円検出
    HoughCircles(gray, circles, CV_HOUGH_GRADIENT, 2.0, 10.0, 160.0, 50.0, 10, 20)
    val pt = Point()
    // 検出した直線上を赤線で塗る
    for (i in 0 until circles.cols()) {
        val data = circles[0, i]
        pt.x = data[0]
        pt.y = data[1]
        val rho = data[2]
        circle(im, pt, rho.toInt(), Scalar(0.0, 200.0, 0.0), 5)
    }
    imwrite("out/tanaka_circles.jpg", im) // 出力画像の保存
}