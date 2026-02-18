package tobybreckon



import org.opencv.core.Mat
import org.opencv.video.Video
import org.opencv.imgproc.Imgproc
import org.opencv.core.Core
import org.opencv.core.Core.*
import org.opencv.imgproc.Imgproc.*
import origami.Camera
import origami.Filter
import origami.Origami

// ********************************************************
// Example : displaying live from attached camera
// and perform background modelling
// Author : Toby Breckon, toby.breckon@durham.ac.uk
// Copyright (c) 2015 Durham University
// License : LGPL - http://www.gnu.org/licenses/lgpl.html
// ********************************************************
// import required OpenCV components
// ********************************************************
fun main(args: Array<String>) {
    Origami.init()
    val foreground = Mat()
    val fg_mask = Mat()
    val MoG = Video.createBackgroundSubtractorMOG2()
    Camera().device(0).filter(Filter()
        {
            MoG.apply(it, fg_mask, 0.1)
            cvtColor(fg_mask, fg_mask, COLOR_GRAY2BGR)
            bitwise_and(it, fg_mask, foreground)
            foreground
        }
    ).run()

}
