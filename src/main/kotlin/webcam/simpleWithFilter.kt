package webcam

import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Imgproc.*
import org.opencv.photo.Photo
import origami.Camera
import origami.Origami
import origami.Origami.*
import java.util.function.Function

fun main(args: Array<String>) {
    init()
    Camera().filter(Function { im ->
        val (temp,target) = listOf(Mat(), Mat())
        val dx = 16.0
        resize(im, temp, Size(dx, dx), 1.0, 1.0, INTER_LINEAR)
        resize(temp, target, im.size(), 1.0, 1.0, INTER_NEAREST)
        target
    }).run()
}