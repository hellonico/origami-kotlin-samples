package tutorialpoint.filtering

import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import origami.Origami


fun main(args: Array<String>) {
    Origami.init()
    val src = Imgcodecs.imread("data/marcel2019.jpg")
    val dst = Mat()
    val kernel = Mat.zeros(5, 5, CvType.CV_32F)
    for (i in 0 until kernel.rows()) {
        for (j in 0 until kernel.cols()) {
            val m = kernel[i, j]
            for (k in 1 until m.size) {
                m[k] = m[k] / (2 * 2)
            }
            kernel.put(i, j, *m)
        }
    }
    println(kernel.dump())
    Imgproc.filter2D(src, dst, -1, kernel)
    Imgcodecs.imwrite("filter2d.jpg", dst)
}
