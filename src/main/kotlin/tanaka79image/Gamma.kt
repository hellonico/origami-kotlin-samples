package tanaka79image

import kotlin.jvm.JvmStatic
import origami.Origami
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.core.CvType
import org.opencv.core.Scalar
import org.opencv.core.Core
import org.opencv.imgcodecs.Imgcodecs.*

fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得
    val gamma = 1.0 // ガンマ定数
    // ルックアップテーブルの計算
    val lut = Mat(1, 256, CvType.CV_8UC1) //　ルックアップテーブル作成
    lut.setTo(Scalar(0.0))
    for (i in 0..255) {
        lut.put(0, i, Math.pow(1.0 * i / 255, 1 / gamma) * 255)
    }
    // ガンマ変換
    Core.LUT(im, lut, im)
    // 画像の出力
    imwrite("out/tanaka_gamma.jpg", im)
}