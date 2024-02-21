package tanaka79image

import org.opencv.core.*
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Imgproc.*
import origami.Origami


fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得
    val mask = Mat() // マスク画像用
    val bgModel = Mat() // 背景モデル用
    val fgModel = Mat() // 前景モデル用
    val rect = Rect(10, 10, 250, 290) // 大まかな前景と背景の境目(矩形)
    val source = Mat(1, 1, CvType.CV_8U, Scalar(3.0))
    grabCut(im, mask, rect, bgModel, fgModel, 1, 0) // グラフカットで前景と背景を分離
    Core.compare(mask, source, mask, Core.CMP_EQ)
    val fg = Mat(im.size(), CvType.CV_8UC1, Scalar(0.0, 0.0, 0.0)) // 前景画像用
    im.copyTo(fg, mask) // 前景画像の作成
    imwrite("out/tanaka_grabcut.jpg", fg) // 画像の出力
}