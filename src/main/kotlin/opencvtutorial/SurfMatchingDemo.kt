package opencvtutorial

import org.opencv.core.Mat
import org.opencv.core.MatOfDMatch
import org.opencv.core.MatOfKeyPoint
import org.opencv.features2d.DescriptorMatcher
import org.opencv.features2d.Features2d
import org.opencv.highgui.HighGui.imshow
import org.opencv.highgui.HighGui.waitKey
import org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.xfeatures2d.SURF
import origami.Origami

fun SURFMatching(args: Array<String>) {
    val filename1 = if (args.size > 1) args[0] else "data/opencvtutorial/box.png"
    val filename2 = if (args.size > 1) args[1] else "data/opencvtutorial/box_in_scene.png"
    val img1 = imread(filename1, IMREAD_GRAYSCALE)
    val img2 = imread(filename2, IMREAD_GRAYSCALE)

    // -- Step 1: Detect the keypoints using SURF Detector, compute the descriptors
    val hessianThreshold = 400.0
    val nOctaves = 4
    val nOctaveLayers = 3
    val extended = false
    val upright = false

    val detector = SURF.create(hessianThreshold, nOctaves, nOctaveLayers, extended, upright)
    val keypoints1 = MatOfKeyPoint()
    val keypoints2 = MatOfKeyPoint()
    val descriptors1 = Mat()
    val descriptors2 = Mat()
    detector.detectAndCompute(img1, Mat(), keypoints1, descriptors1)
    detector.detectAndCompute(img2, Mat(), keypoints2, descriptors2)

    // -- Step 2: Matching descriptor vectors with a brute force matcher
    // Since SURF is a floating-point descriptor NORM_L2 is used
    val matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE)
    val matches = MatOfDMatch()
    matcher.match(descriptors1, descriptors2, matches)

    // -- Draw matches
    val imgMatches = Mat()
    Features2d.drawMatches(img1, keypoints1, img2, keypoints2, matches, imgMatches)
    imshow("Matches", imgMatches)
    waitKey(0)
}

fun main(args: Array<String>) {
    Origami.init()
    SURFMatching(args)
}
