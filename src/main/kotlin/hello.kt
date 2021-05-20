
import org.opencv.core.CvType.CV_8UC1
import org.opencv.core.Mat.*
import origami.Origami

object HelloCV {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        Origami.init()
        val hello = eye(3, 3, CV_8UC1)
        println(hello.dump())
    }

}
