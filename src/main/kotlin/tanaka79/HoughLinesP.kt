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
    Canny(gray, gray, 80.0, 100.0) // 輪郭線検出
    val lines = Mat()
    // 確率的ハフ変換で直線検出
    HoughLinesP(gray, lines, 1.0, Math.PI / 180, 50, 100.0, 50.0)
    var data: DoubleArray
    val pt1 = Point()
    val pt2 = Point()
    // 検出した直線上を赤線で塗る
    for (i in 0 until lines.cols()) {
        data = lines[0, i]
        pt1.x = data[0]
        pt1.y = data[1]
        pt2.x = data[2]
        pt2.y = data[3]
        line(im, pt1, pt2, Scalar(0.0, 0.0, 200.0), 3)
    }
    imwrite("out/tanaka_houghlinesp.jpg", im) // 出力画像の保存
}