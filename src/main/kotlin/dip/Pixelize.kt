package dip

import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc.*
import origami.Origami.init

/**
 * https://stackoverflow.com/questions/55508615/how-to-pixelate-image-using-opencv-in-python
 */
fun main(args: Array<String>) {
    init()

    val (source, temp,target) = listOf(imread("data/bear.png"), Mat(),Mat())
     val (w, h) = listOf(16.0, 16.0)
//    val (w, h) = listOf(64.0, 64.0)

    resize(source, temp, Size(w, h), 1.0,1.0, INTER_LINEAR)
    resize(temp, target, source.size(), 1.0,1.0,INTER_NEAREST)

    imwrite("out/pixelized.jpg", target)
}
