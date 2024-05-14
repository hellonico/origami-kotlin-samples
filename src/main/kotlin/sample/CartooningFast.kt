package sample

import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.highgui.HighGui.imshow
import org.opencv.highgui.HighGui.waitKey
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc.*
import org.opencv.videoio.VideoCapture
import org.scijava.nativelib.NativeLoader
import origami.Origami
import java.util.*
import kotlin.system.exitProcess

class CartooningFast {

    var d = 13
    var sigmaColor = d
    var sigmaSpace = 7
    var ksize = 7

    var maxValue = 255.0
    var blockSize = 9
    var C = 2

    fun cartoon(inputFrame: Mat): Mat {
        val gray = Mat()
        val co = Mat()

        cvtColor(inputFrame, co, COLOR_BGR2GRAY)
        bilateralFilter(co, gray, d, sigmaColor.toDouble(), sigmaSpace.toDouble())
        medianBlur(gray, gray, ksize)
        adaptiveThreshold(gray, gray, maxValue, ADAPTIVE_THRESH_MEAN_C, THRESH_BINARY,
                blockSize, C.toDouble())

        val m = Mat()
        cvtColor(gray, m, COLOR_GRAY2BGR)

        val mOutputFrame = Mat()
        Core.bitwise_and(inputFrame, m, mOutputFrame)
        return mOutputFrame
    }

    fun capturing() {
        Core.setNumThreads(4)
        val vc = VideoCapture()
        vc.open(0)
        val image = Mat()
        var key = -1
        while (key == -1) {
            vc.read(image)
            imshow("cartoon", cartoon(image))
            key = waitKey(30)
        }
        imwrite(String.format("cartoon/cartoon2_%s.png", Date().time), cartoon(image))
    }

    init {
        capturing()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            Origami.init()
            CartooningFast()
            exitProcess(0)
        }
    }

}