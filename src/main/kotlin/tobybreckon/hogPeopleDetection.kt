package tobybreckon

import org.opencv.core.*
import org.opencv.highgui.HighGui
import org.opencv.highgui.HighGui.imshow
import kotlin.Throws
import java.lang.InterruptedException
import kotlin.jvm.JvmStatic
import origami.Origami
import org.opencv.objdetect.HOGDescriptor
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Imgproc.*
import org.opencv.videoio.VideoCapture
import origami.Camera
import origami.Filter
import java.lang.Exception

// ********************************************************
// Example : displaying live from attached camera
// and (try to!) detect people in it using HOG
// Author : Toby Breckon, toby.breckon@durham.ac.uk
// Copyright (c) 2015 Durham University
// License : LGPL - http://www.gnu.org/licenses/lgpl.html
// ********************************************************
// import required OpenCV components
// ********************************************************
fun main(args: Array<String>) {
    Origami.init()
    val HOG = HOGDescriptor()
    HOG.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector())

    val foundLocations = MatOfRect()
    val foundWeights = MatOfDouble()
    val color = Scalar(255.0, 0.0, 0.0)

    Camera().device(0).filter(Filter() {
        HOG.detectMultiScale(
            it, foundLocations, foundWeights, 0.0, Size(8.0, 8.0), Size(32.0, 32.0),
            1.05, 8.0, false
        )
        drawLocations(foundLocations, it, color)
    }).run()
}

private fun drawLocations(foundLocations: MatOfRect, it: Mat?, color: Scalar): Mat? {
    val rectangles = foundLocations.toList()
    for (i in rectangles.indices) {
        rectangle(
            it, Point(rectangles[i].x.toDouble(), rectangles[i].y.toDouble()),
            Point(
                (rectangles[i].x + rectangles[i].width).toDouble(),
                (rectangles[i].y + rectangles[i].height).toDouble()
            ),
            color, 2, 1, 0
        )
    }
    return it
}