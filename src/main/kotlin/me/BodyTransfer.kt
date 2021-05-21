package me

import org.opencv.core.MatOfRect
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.CascadeClassifier
import origami.Origami
import origami.utils.Downloader

const val DEFAULT_CLASSIFIER =
    "https://raw.githubusercontent.com/opencv/opencv/master/data/haarcascades/haarcascade_upperbody.xml"
const val DEFAULT_IMAGE = "https://www.netclipart.com/pp/m/106-1066497_suit-man-png-man-in-suit-png.png"
//const val IMAGE_PATH = "data/image.jpg"
const val CLASSIFIER_PATH = "haarcascade.xml"
val COLOR = Scalar(0.0, 100.0, 0.0)

fun main(args: Array<String>) {
    Origami.init()
    val imageUrl = if (args.size >= 1 && args[0] != null) args[0] else DEFAULT_IMAGE
    val classifierUrl = if (args.size >= 2 && args[1] != null) args[1] else DEFAULT_CLASSIFIER
    Downloader.transfer(imageUrl, "data/image.jpg")
    Downloader.transfer(classifierUrl, CLASSIFIER_PATH)
    val classifier = CascadeClassifier()
    classifier.load(CLASSIFIER_PATH)
    val mat = imread("data/image.jpg")
    val bodies = MatOfRect()
    classifier.detectMultiScale(mat, bodies)

    for (body in bodies.toList()) {
        Imgproc.rectangle(
            mat,
            Point(body.x.toDouble(), body.y.toDouble()),
            Point((body.x + body.width).toDouble(), (body.y + body.height).toDouble()),
            COLOR,
            3
        )
    }

    imwrite("out/bodytransfer.jpg", mat)
}