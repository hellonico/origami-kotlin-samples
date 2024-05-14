package sample

import org.opencv.core.*
import org.opencv.dnn.Dnn.*
import org.opencv.imgcodecs.Imgcodecs.*
import org.scijava.nativelib.NativeLoader
import origami.Origami
import origami.Origami.*

object TensorFlowTest {

    private fun normAssert(ref: Mat, test: Mat) {
        val normL1 = Core.norm(ref, test, Core.NORM_L1) / ref.total()
        val normLInf = Core.norm(ref, test, Core.NORM_INF) / ref.total()
        println(normL1)
        println(normLInf)
    }

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        init()

        val sourceImageFile = "data/tf/grace_hopper_227.png"
        val tfnetFile = "data/tf/tensorflow_inception_graph.pb"

        val net = readNetFromTensorflow(tfnetFile)
        val image = imread(sourceImageFile)
        val inputBlob = blobFromImage(image, 1.0, Size(224.0, 224.0), Scalar(0.0), true, true)
        net.setInput(inputBlob, "input")

        net.setPreferableBackend(DNN_BACKEND_OPENCV)

        var result = net.forward("softmax2")
        result = result.reshape(1, 1)
        val minmax = Core.minMaxLoc(result)

        // check scores
        val top5RefScores = MatOfFloat(0.63032645f, 0.2561979f, 0.032181446f, 0.015721032f, 0.014785315f).reshape(1, 1)

        Core.sort(result, result, Core.SORT_DESCENDING)
        normAssert(result.colRange(0, 5), top5RefScores)

    }

}
