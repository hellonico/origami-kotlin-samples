package tanaka79

import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.TermCriteria
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import origami.Origami
import java.util.*


fun main(args: Array<String>) {
    Origami.init()
    val img = imread("data/lupin3.jpeg") // 入力画像の取得
    val k = 2
    val clusters = cluster(img, k)[0]
    imwrite("out/tanaka_cluster.png", clusters) // 画像をJPG形式で保存
}

fun cluster(cutout: Mat, k: Int): List<Mat> {
    val samples = cutout.reshape(1, cutout.cols() * cutout.rows())
    val samples32f = Mat()
    samples.convertTo(samples32f, CvType.CV_32F, 1.0 / 255.0)
    val labels = Mat()
    val criteria = TermCriteria(TermCriteria.COUNT, 100, 1.0)
    val centers = Mat()
    Core.kmeans(samples32f, k, labels, criteria, 1, Core.KMEANS_PP_CENTERS, centers)
    return showClusters(cutout, labels, centers)
}

private fun showClusters(cutout: Mat, labels: Mat, centers: Mat): List<Mat> {
    centers.convertTo(centers, CvType.CV_8UC1, 255.0)
    centers.reshape(3)
    val clusters: MutableList<Mat> = ArrayList()
    for (i in 0 until centers.rows()) {
        clusters.add(Mat.zeros(cutout.size(), cutout.type()))
    }
    val counts: MutableMap<Int, Int> = HashMap()
    for (i in 0 until centers.rows()) counts[i] = 0
    var rows = 0
    for (y in 0 until cutout.rows()) {
        for (x in 0 until cutout.cols()) {
            val label = labels[rows, 0][0].toInt()
            val r = centers[label, 2][0].toInt()
            val g = centers[label, 1][0].toInt()
            val b = centers[label, 0][0].toInt()
            clusters[label].put(y, x, b.toDouble(), g.toDouble(), r.toDouble())
            rows++
        }
    }
    return clusters
}