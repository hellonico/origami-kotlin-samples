package tanaka79

import org.opencv.core.Mat
import org.opencv.core.MatOfKeyPoint
import org.opencv.features2d.Features2d
import org.opencv.features2d.SIFT
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.COLOR_RGB2GRAY
import org.opencv.imgproc.Imgproc.cvtColor
import origami.Origami


fun main(args: Array<String>) {
    Origami.init()
    val im = imread("data/lupin3.jpeg") // 入力画像の取得
    val gray = Mat()
    cvtColor(im, gray, COLOR_RGB2GRAY) // 画像のグレースケール変換

    // ------ SIFTの処理 ここから ------

    val siftDetector = SIFT.create()
    val kp = MatOfKeyPoint()
    siftDetector.detect(gray, kp)

//    val des = Mat(im.rows(), im.cols(), im.type());
//    siftDetector.compute(gray, kp, des);

    // -- Draw keypoints
    Features2d.drawKeypoints(im, kp, im)
    imwrite("out/tanaka_sift.jpg", im) // 画像の出力
}