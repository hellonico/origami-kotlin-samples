package sample

import org.opencv.core.Core
import org.opencv.core.MatOfKeyPoint
import org.opencv.features2d.Features2d.*
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.xfeatures2d.SURF
import org.scijava.nativelib.NativeLoader
import origami.Origami
import origami.Origami.*

internal class SURFDetection {
    fun run(args: Array<String>) {
        val filename = if (args.isNotEmpty()) args[0] else "data/box.png"

        val src = imread(filename, IMREAD_GRAYSCALE)
        if (src.empty()) {
            System.err.println("Cannot read image: $filename")
            System.exit(0)
        }

        // -- Step 1: Detect the keypoints using SURF Detector
        val hessianThreshold = 400.0
        val nOctaves = 4
        val nOctaveLayers = 3
        val extended = false
        val upright = false
        val detector = SURF.create(hessianThreshold, nOctaves, nOctaveLayers, extended, upright)
        val keyPoints = MatOfKeyPoint()
        detector.detect(src, keyPoints)

        // -- Draw keypoints
        drawKeypoints(src, keyPoints, src)

        // -- Show detected (drawn) keypoints
        imwrite("surf_result.png", src)
        System.exit(0)
    }
}

object SurfDemo {

    @JvmStatic
    fun main(args: Array<String>) {
        init()
        SURFDetection().run(args)
    }
}
