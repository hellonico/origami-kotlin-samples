package dip

import kotlin.Throws
import java.io.IOException
import kotlin.jvm.JvmStatic
import origami.Origami
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.awt.image.DataBufferByte
import org.opencv.core.Mat
import org.opencv.core.CvType
import org.opencv.core.Core
import org.opencv.core.Core.*
import java.io.File

fun main(args: Array<String>) {
    Origami.init()
    val input = File("data/dip/digital_image_processing.jpg")
    val image = ImageIO.read(input)
    val data = (image.raster.dataBuffer as DataBufferByte).data
    val mat = Mat(image.height, image.width, CvType.CV_8UC3)
    mat.put(0, 0, data)
    val mat1 = Mat(image.height, image.width, CvType.CV_8UC3)
    flip(mat, mat1, -1)
    val data1 = ByteArray(mat1.rows() * mat1.cols() * mat1.elemSize().toInt())
    mat1[0, 0, data1]
    val image1 = BufferedImage(mat1.cols(), mat1.rows(), 5)
    image1.raster.setDataElements(0, 0, mat1.cols(), mat1.rows(), data1)
    val outout = File("hsv.jpg")
    ImageIO.write(image1, "jpg", outout)
}