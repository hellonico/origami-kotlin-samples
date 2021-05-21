package tanaka79

import org.opencv.core.MatOfRect
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.rectangle
import org.opencv.objdetect.CascadeClassifier
import origami.Origami


fun main(args: Array<String>) {
    Origami.init()
    // 入力画像の取得
    val im = imread("data/lupin3.jpeg")
    // カスケード分類器でアニメ顔探索
    val faceDetector = CascadeClassifier("data/nagadomi/lbpcascade_animeface.xml")
    val faceDetections = MatOfRect()
    faceDetector.detectMultiScale(im, faceDetections)
    // 見つかったアニメ顔を矩形で囲む
    for (rect in faceDetections.toArray()) {
        rectangle(
            im,
            Point(rect.x.toDouble(), rect.y.toDouble()),
            Point((rect.x + rect.width).toDouble(), (rect.y + rect.height).toDouble()),
            Scalar(0.0, 0.0, 255.0),
            5
        )
    }
    // 結果を保存
    imwrite("out/anime_face.png", im)
}