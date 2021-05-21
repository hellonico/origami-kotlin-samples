package tanaka79image

import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Imgproc.*
import origami.Origami


fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得
    val tmp = imread("data/lupin_head.jpg") // テンプレート画像の取得
    val result = Mat()
    matchTemplate(im, tmp, result, TM_CCOEFF_NORMED) //テンプレートマッチング
    threshold(result, result, 0.8, 1.0, THRESH_TOZERO) // 検出結果から相関係数がしきい値以下の部分を削除
    // テンプレート画像の部分を元画像に赤色の矩形で囲む

    for (i in 0 until result.rows()) {
        for (j in 0 until result.cols()) {
            if (result[i, j][0] > 0) {
                rectangle(
                    im,
                    Point(j.toDouble(), i.toDouble()),
                    Point((j + tmp.cols()).toDouble(), (i + tmp.rows()).toDouble()),
                    Scalar(0.0, 0.0, 255.0)
                )
            }
        }
    }
    imwrite("out/tanaka_match.jpg", im) // 画像の出力
}