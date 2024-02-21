package tanaka79

import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import origami.Origami


fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得
    val n = 100 // 大きいほど階調数が減少
    // 減色処理
    val sz = im.size()
    var i = 0
    while (i < sz.height) {
        var j = 0
        while (j < sz.width) {
            val pixcel = im[i, j]
            pixcel[0] = (pixcel[0].toInt() / n * n + n / 2).toDouble()
            pixcel[1] = (pixcel[1].toInt() / n * n + n / 2).toDouble()
            pixcel[2] = (pixcel[2].toInt() / n * n + n / 2).toDouble()
            im.put(i, j, *pixcel)
            j++
        }
        i++
    }
    imwrite("out/tanaka_level.jpg", im) // 画像データをJPG形式で保存
}
