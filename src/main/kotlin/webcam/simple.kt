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
    Camera().run()
}