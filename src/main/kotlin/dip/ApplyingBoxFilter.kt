package dip

import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import origami.Origami

fun createKernelOfSize(kernelSize: Int): Mat {
    val kernel = Mat.ones(kernelSize, kernelSize, CvType.CV_32F)
    for (i in 0 until kernel.rows()) {
        for (j in 0 until kernel.cols()) {
            val m = kernel[i, j]
            for (k in m.indices) {
                m[k] = m[k] / (kernelSize * kernelSize)
            }
            kernel.put(i, j, *m)
        }
    }
    return kernel
}

fun main(args: Array<String>) {
    Origami.init()
    val source = Imgcodecs.imread("data/dip/grayscale.jpg", Imgcodecs.IMREAD_GRAYSCALE)
    val destination = Mat(source.rows(), source.cols(), source.type())
    val kernel5 = createKernelOfSize(5)
    Imgproc.filter2D(source, destination, -1, kernel5)
    Imgcodecs.imwrite("boxfilterKernel5.jpg", destination)
    val kernel9 = createKernelOfSize(9)
    Imgproc.filter2D(source, destination, -1, kernel9)
    Imgcodecs.imwrite("boxfilterKernel9.jpg", destination)
}