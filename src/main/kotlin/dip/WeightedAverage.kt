package dip

import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Imgproc.filter2D
import origami.Origami

fun main(args: Array<String>) {
    Origami.init()
    val kernelSize = 9
    val source = imread("data/dip/grayscale.jpg", Imgcodecs.IMREAD_GRAYSCALE)
    val destination = Mat(source.rows(), source.cols(), source.type())
    val kernel = Mat.ones(kernelSize, kernelSize, CvType.CV_32F)
    for (i in 0 until kernel.rows()) {
        for (j in 0 until kernel.cols()) {
            val m = kernel[i, j]
            for (k in m.indices) {
                if (i == 1 && j == 1) {
                    m[k] = (10 / 18).toDouble()
                } else {
                    m[k] = m[k] / 18
                }
            }
            kernel.put(i, j, *m)
        }
    }
    filter2D(source, destination, -1, kernel)
    imwrite("out/weightedaveragefilter.jpg", destination)
}