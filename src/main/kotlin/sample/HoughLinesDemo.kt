package sample

import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.highgui.HighGui.*
import org.opencv.imgcodecs.Imgcodecs.*
import org.opencv.imgproc.Imgproc.*
import org.scijava.nativelib.NativeLoader.*
import origami.Origami
import java.lang.System.exit

internal class HoughLinesRun {

    fun run(args: Array<String>) {
        // Declare the output variables
        val dst = Mat()
        val cdst = Mat()
        val cdstP: Mat

        // ! [load]
        val default_file = "data/sudoku.png"
        val filename = if (args.size > 0) args[0] else default_file

        // Load an image
        val src = imread(filename, IMREAD_GRAYSCALE)

        // Check if image is loaded fine
        if (src.empty()) {
            println("Error opening image!")
            println("Program Arguments: [image_name -- default $default_file] \n")
            exit(-1)
        }
        // ! [load]

        // ! [edge_detection]
        // Edge detection
        Canny(src, dst, 50.0, 200.0, 3, false)
        // ! [edge_detection]

        // Copy edges to the images that will display the results in BGR
        cvtColor(dst, cdst, COLOR_GRAY2BGR)
        cdstP = cdst.clone()

        // ! [hough_lines]
        // Standard Hough Line Transform
        val lines = Mat() // will hold the results of the detection
        HoughLines(dst, lines, 1.0, Math.PI / 180, 150) // runs the actual detection
        // ! [hough_lines]
        // ! [draw_lines]
        // Draw the lines
        for (x in 0 until lines.rows()) {
            val rho = lines.get(x, 0)[0]
            val theta = lines.get(x, 0)[1]

            val a = Math.cos(theta)
            val b = Math.sin(theta)
            val x0 = a * rho
            val y0 = b * rho
            val pt1 = Point(Math.round(x0 + 1000 * -b).toDouble(), Math.round(y0 + 1000 * a).toDouble())
            val pt2 = Point(Math.round(x0 - 1000 * -b).toDouble(), Math.round(y0 - 1000 * a).toDouble())
            line(cdst, pt1, pt2, Scalar(0.0, 0.0, 255.0), 3, LINE_AA, 0)
        }
        // ! [draw_lines]

        // ! [hough_lines_p]
        // Probabilistic Line Transform
        val linesP = Mat() // will hold the results of the detection
        HoughLinesP(dst, linesP, 1.0, Math.PI / 180, 50, 50.0, 10.0) // runs the actual detection
        // ! [hough_lines_p]
        // ! [draw_lines_p]
        // Draw the lines
        for (x in 0 until linesP.rows()) {
            val l = linesP.get(x, 0)
            line(cdstP, Point(l[0], l[1]), Point(l[2], l[3]), Scalar(0.0, 0.0, 255.0), 3, LINE_AA,
                    0)
        }
        // ! [draw_lines_p]

        // ! [imshow]
        // Show results
        imshow("Source", src)
        imshow("Detected Lines (in red) - Standard Hough Line Transform", cdst)
        imshow("Detected Lines (in red) - Probabilistic Line Transform", cdstP)
        // ! [imshow]

        // ! [exit]
        // Wait and Exit
        waitKey()
        exit(0)
        // ! [exit]
    }
}

object HoughLines {

    @JvmStatic
    fun main(args: Array<String>) {
        Origami.init()
        HoughLinesRun().run(args)
    }
}
