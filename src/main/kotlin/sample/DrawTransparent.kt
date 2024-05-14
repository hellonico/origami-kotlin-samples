package sample

import java.io.IOException
import java.util.ArrayList

import org.opencv.core.Core.*
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs.*
import org.scijava.nativelib.NativeLoader.*
import origami.Origami
import origami.Origami.*

object DrawTransparent {

    internal fun drawTransparency(frame: Mat, transp: Mat, xPos: Int, yPos: Int) {
        val layers = ArrayList<Mat>()
        split(transp, layers)
        val mask = layers.removeAt(3)
        merge(layers, transp)
        val submat = frame.submat(yPos, yPos + transp.rows(), xPos, xPos + transp.cols())
        transp.copyTo(submat, mask)
    }

    internal fun weightedTransparency(frame: Mat, transp: Mat, xPos: Int, yPos: Int) {
        val layers = ArrayList<Mat>()
        split(transp, layers)

        // transparency used as the mask
        val mask = layers.removeAt(3)
        // we need an inverted mask later on
        bitwise_not(mask, mask)

        val merged = Mat()
        merge(layers, merged)

        val submat = frame.submat(yPos, yPos + transp.rows(), xPos, xPos + transp.cols())
        val source = submat.clone()

        addWeighted(merged, 0.5, source, 0.5, 0.0, submat)

        source.copyTo(submat, mask)
    }

    fun marcelAndWhool() {
        val marcel = imread("data/marcel2019.jpg")
        val whool = imread("data/tp/whool.png", IMREAD_UNCHANGED)
        drawTransparency(marcel, whool, 50, 50)
        imwrite("target/marcelandwhool.jpg", marcel)
    }

    fun marcelAndSheep() {
        val marcel = imread("data/marcel2019.jpg")
        var sheep = imread("data/tp/sheep.png", IMREAD_UNCHANGED)
        weightedTransparency(marcel, sheep, 50, 50)
        sheep = imread("data/tp/sheep.png", IMREAD_UNCHANGED)
        weightedTransparency(marcel, sheep, 400, 500)
        imwrite("target/marcelandsheep.jpg", marcel)
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        init()
        marcelAndWhool()
        marcelAndSheep()
    }
}