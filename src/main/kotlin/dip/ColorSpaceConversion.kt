package dip

import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Imgproc.*
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val mat = imread("data/dip/digital_image_processing.jpg")
    val mat1 = Mat(mat.width(), mat.height(), CvType.CV_8UC3)
    cvtColor(mat, mat1, COLOR_RGB2HSV)
    imwrite("out/hsv.jpg", mat1)
}