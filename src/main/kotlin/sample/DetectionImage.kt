package sample

import org.opencv.core.Core.*
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc.*
import org.scijava.nativelib.NativeLoader.*
import origami.Origami
import origami.Origami.*
import java.io.IOException

object TemplateMatching {

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {

        init()

        val filePath = "data/detectionimages/"
        val source = imread(filePath + "kapadokya.jpg")
        val template = imread(filePath + "balon.jpg")

        val outputImage = Mat()
        matchTemplate(source, template, outputImage, TM_CCOEFF)

        val mmr = minMaxLoc(outputImage)
        val matchLoc = mmr.maxLoc

        rectangle(source, matchLoc, Point(matchLoc.x + template.cols(),
                matchLoc.y + template.rows()), Scalar(255.0, 255.0, 255.0))

        imwrite("sonuckotling.jpg", source)

    }

}
