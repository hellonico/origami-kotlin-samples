package sample

import org.opencv.core.Mat
import org.opencv.highgui.HighGui.*
import org.opencv.videoio.VideoCapture
import origami.Origami
import java.awt.Color
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.JPanel

class CVWebcam : JPanel {

    internal lateinit var image: BufferedImage

    override fun paint(g: Graphics?) {
        g!!.drawImage(image, 0, 0, this)
    }

    constructor() {}

    constructor(img: BufferedImage) {
        image = img
    }

    // Show image on window
    fun window(img: BufferedImage, text: String, x: Int, y: Int) {
        val frame0 = JFrame()
        frame0.contentPane.add(CVWebcam(img))
        frame0.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame0.title = text
        frame0.setSize(img.width, img.height + 30)
        frame0.setLocation(x, y)
        frame0.isVisible = true
    }

    // Load an image
    fun loadImage(file: String): BufferedImage? {
        val img: BufferedImage

        try {
            val input = File(file)
            img = ImageIO.read(input)

            return img
        } catch (e: Exception) {
            println("erro")
        }

        return null
    }

    // Save an image
    fun saveImage(img: BufferedImage) {
        try {
            val outputfile = File("Images/new.png")
            ImageIO.write(img, "png", outputfile)
        } catch (e: Exception) {
            println("error")
        }

    }

    // Grayscale filter
    fun grayscale(img: BufferedImage): BufferedImage {
        for (i in 0 until img.height) {
            for (j in 0 until img.width) {
                val c = Color(img.getRGB(j, i))

                val red = (c.red * 0.299).toInt()
                val green = (c.green * 0.587).toInt()
                val blue = (c.blue * 0.114).toInt()

                val newColor = Color(red + green + blue, red + green + blue, red + green + blue)

                img.setRGB(j, i, newColor.rgb)
            }
        }

        return img
    }

    companion object {
        init {
            Origami.init()
        }

        @Throws(InterruptedException::class)
        @JvmStatic
        fun main(args: Array<String>) {

            val t = CVWebcam()
            val webcamI = if(args.size > 0) Integer.parseInt(args[0]) else 0
            val camera = VideoCapture()
            camera.open(webcamI)

            val frame = Mat()
            camera.read(frame)

            if (!camera.isOpened) {
                println("Error")
            } else {
                while (true) {
                    if (camera.read(frame)) {
                        val image = toBufferedImage(frame) as BufferedImage
                        t.window(image, "Original Image", 0, 0)
                        t.window(t.grayscale(image), "Processed Image", 40, 60)
                        break
                    }
                }
            }
            camera.release()
        }
    }

}