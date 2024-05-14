package sample

import org.opencv.core.*
import org.opencv.dnn.Dnn
import org.opencv.dnn.Net
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc
import org.scijava.nativelib.NativeLoader.loadLibrary
import origami.Origami
import origami.Origami.*
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

object YoloV3Demo {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        init()
        val tfnetFile = "data/yolov3/yolov3.weights"
        val protoFil = "data/yolov3/yolov3.cfg"
        val input = arrayOf("data/yolov3/bird.jpg")
        val labels = Files.readAllLines(Paths.get("data/yolov3/coco.names"))
        runDark(tfnetFile, protoFil,labels, input)
    }

    @Throws(IOException::class)
    private fun runDark(tfnetFile:String, protoFil:String, labels:List<String>, sourceImageFile: Array<String>) {
        val net = Dnn.readNetFromDarknet(protoFil, tfnetFile)
        val layers = getOutputsNames(net)
        for (image in sourceImageFile) {
            runInference(net, layers, labels, image)
        }
    }

    private fun getOutputsNames(net: Net): List<String> {
        return net.unconnectedOutLayers.toList().map{i -> net.layerNames[i-1] };
    }

    const val IN_WIDTH = 416
    const val IN_HEIGHT = 416
    const val IN_SCALE_FACTOR = 0.00392157
    const val MAX_RESULTS = 20
    private fun runInference(net: Net, layers: List<String>, labels: List<String>, filename: String) {
        val frame = imread(filename, IMREAD_COLOR)

        val blob = Dnn.blobFromImage(frame, IN_SCALE_FACTOR, Size(IN_WIDTH.toDouble(), IN_HEIGHT.toDouble()), Scalar(0.0, 0.0, 0.0), true)
        net.setInput(blob)

        val outputs = ArrayList<Mat>()
        for (i in layers.indices)
            outputs.add(Mat())

        net.forward(outputs, layers)

        val labelIDs = ArrayList<Int>()
        val probabilities = ArrayList<Float>()
        val locations = ArrayList<Any>()

        postprocess(filename, frame, labels, outputs, labelIDs, probabilities, locations, MAX_RESULTS)
    }

    private fun postprocess(filename: String, frame: Mat, labels: List<String>, outs: List<Mat>, classIds: MutableList<Int>, confidences: MutableList<Float>, locations: List<*>, nResults: Int) {
        val tmpLocations = ArrayList<Rect2d>()
        val tmpClasses = ArrayList<Int>()
        val tmpConfidences = ArrayList<Float>()
        val w = frame.width()
        val h = frame.height()

        for (out in outs) {

            println(">"+out.dims())
            // Scan through all the bounding boxes output from the network and keep only the
            // ones with high confidence scores. Assign the box's class label as the class
            // with the highest score for the box.
            val data = FloatArray(out.total().toInt())
            out.get(0, 0, data)
            var k = 0
            for (j in 0 until out.height()) {

                // Each row of data has 4 values for location, followed by N confidence values which correspond to the labels
                val scores = out.row(j).colRange(5, out.width())
                // Get the value and location of the maximum score
                val result = Core.minMaxLoc(scores)

                if (result.maxVal > 0) {
//                    println(out.row(j))
                    val center_x = data[k + 0] * w
                    val center_y = data[k + 1] * h
                    val width = data[k + 2] * w
                    val height = data[k + 3] * h
                    val left = center_x - width / 2
                    val top = center_y - height / 2

                    tmpClasses.add(result.maxLoc.x.toInt())
                    tmpConfidences.add(result.maxVal.toFloat())
                    val box = Rect2d(left.toDouble(), top.toDouble(), width.toDouble(), height.toDouble())
//                    println(">"+result.maxLoc.x+":"+result.maxLoc.y+"<>"+result.maxLoc);
//                    println(box)
//                    println(out.row(j).colRange(0,5).dump())
                    tmpLocations.add(box)

                }
                k += out.width()
            }
        }
        println(tmpClasses.size)

        annotateFrame(frame, labels, classIds, confidences, nResults, tmpLocations, tmpClasses, tmpConfidences)
        imwrite("out/" + File(filename).name, frame)
    }

    private fun annotateFrame(frame: Mat, labels: List<String>, classIds: MutableList<Int>, confidences: MutableList<Float>, nResults: Int, tmpLocations: List<Rect2d>, tmpClasses: List<Int>, tmpConfidences: List<Float>) {
        // Perform non maximum suppression to eliminate redundant overlapping boxes with
        // lower confidences and sort by confidence

        // many overlapping results coming from yolo so have to use it
        val locMat = MatOfRect2d()
        locMat.fromList(tmpLocations)
        val confidenceMat = MatOfFloat()
        confidenceMat.fromList(tmpConfidences)
        val indexMat = MatOfInt()
        Dnn.NMSBoxes(locMat, confidenceMat, 0.1f, 0.1f, indexMat)

        println(">"+indexMat.rows())

        var i = 0
        while (i < indexMat.total() && i < nResults) {
            val idx = indexMat.get(i, 0)[0].toInt()
            classIds.add(tmpClasses[idx])
            confidences.add(tmpConfidences[idx])
            val box = tmpLocations[idx]
            val label = labels[classIds[i]]
            Imgproc.rectangle(frame, Rect(box.tl(),box.br()), Scalar(0.0, 0.0, 0.0), 2)
            Imgproc.putText(frame, label, Point(box.x.toDouble(), box.y.toDouble()), Imgproc.FONT_HERSHEY_PLAIN, 1.0, Scalar(0.0, 0.0, 0.0), 2)
            ++i

        }
    }
}
