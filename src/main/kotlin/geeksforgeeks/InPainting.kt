package geeksforgeeks

import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs.imread
import org.opencv.imgcodecs.Imgcodecs.imwrite
import org.opencv.photo.Photo.INPAINT_NS
import org.opencv.photo.Photo.inpaint
import origami.Origami

/**
 * https://www.geeksforgeeks.org/introduction-to-opencv/
 */

fun main(args: Array<String>) {
    Origami.init()
    val img = imread("data/geeksforgeeks/cat_damaged.png")
    val mask = imread("data/geeksforgeeks/cat_mask.png", 0)
    val dst = Mat()
    inpaint(img, mask, dst, 3.0, INPAINT_NS)
    imwrite("out/cat_inpainted.png", dst)
}