package tanaka79

import org.opencv.core.MatOfRect
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.CascadeClassifier
import origami.Origami


fun main(args: Array<String>) {
    Origami.init()
    // 入力画像の取得
    val im = Imgcodecs.imread("data/image.jpg")
    // カスケード分類器で顔探索
    val faceDetector = CascadeClassifier("data/haarcascades/haarcascade_frontalface_alt.xml")
    val faceDetections = MatOfRect()
    faceDetector.detectMultiScale(im, faceDetections)
    // 見つかった顔を矩形で囲む
    for (rect in faceDetections.toArray()) {
        Imgproc.rectangle(
            im,
            Point(rect.x.toDouble(), rect.y.toDouble()),
            Point((rect.x + rect.width).toDouble(), (rect.y + rect.height).toDouble()),
            Scalar(0.0, 0.0, 255.0),
            5
        )
    }
    // 結果を保存
    Imgcodecs.imwrite("tanaka.jpg", im)
}