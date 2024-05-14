package sample

import org.opencv.core.Core.NATIVE_LIBRARY_NAME
import org.opencv.core.Core.minMaxLoc
import org.opencv.core.Scalar
import org.opencv.core.Size
import org.opencv.dnn.Dnn.*
import org.opencv.imgcodecs.Imgcodecs.imread
import org.scijava.nativelib.NativeLoader.loadLibrary
import origami.Origami
import origami.Origami.*

object CaffeeDemo {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        init()

        val sourceImageFile = "data/caffee/jeunehomme.jpg"
        val tfnetFile = "data/caffee/gender_net.caffemodel"
        val protoFil = "data/caffee/gender.prototxt"
        val labels = listOf("男", "女")
        sourceImageFile.runCaffeeNetwork(tfnetFile, protoFil, labels)

    }

    private fun String.runCaffeeNetwork(tfnetFile: String, protoFil: String, labels: List<*>) {
        val net = readNetFromCaffe(protoFil, tfnetFile)
//        val layernames = net.layerNames
//        println(layernames)

        val image = imread(this)
        val inputBlob = blobFromImage(image, 1.0, Size(256.0, 256.0), Scalar(0.0), true, true)
        net.setInput(inputBlob)
        net.setPreferableBackend(DNN_BACKEND_OPENCV)
        val result = net.forward()

        println(result.dump())

        val minmax = minMaxLoc(result)
        println(labels[minmax.maxLoc.x.toInt()])
    }

}
