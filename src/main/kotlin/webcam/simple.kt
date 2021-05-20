package webcam

import org.opencv.photo.Photo
import origami.Camera
import origami.Origami
import java.util.function.Function

fun main(args: Array<String>) {
    Origami.init()
    Camera().device(1).filter(Function { im ->
        Photo.fastNlMeansDenoising(im, im)
        im
    }).run()
}