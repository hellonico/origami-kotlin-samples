package sample

import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.IOException
import java.util.ArrayList

import javax.swing.JFrame
import javax.swing.JPanel

import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.MatOfRect
import org.opencv.core.Size
import org.opencv.highgui.HighGui
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc.*
import org.opencv.objdetect.CascadeClassifier
import org.opencv.videoio.VideoCapture
import org.opencv.videoio.Videoio
import org.scijava.nativelib.NativeLoader
import origami.Origami
import origami.Origami.*

// Create a constructor method

internal class My_Panel : JPanel() {
    var image: BufferedImage? = null

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        if (this.image == null)
            return
        g.drawImage(this.image, 10, 10, 2 * this.image!!.width, 2 * this.image!!.height, null)
        g.drawString("This is my custom Panel!", 10, 20)
    }

    companion object {
        private val serialVersionUID = 1L
    }
}

internal class processor// Create a constructor method
(_mask: String, private val adjust: Double) {
    private val face_cascade: CascadeClassifier
    private val mask: Mat

    init {
        mask = Imgcodecs.imread(_mask, Imgcodecs.IMREAD_UNCHANGED)

        face_cascade = CascadeClassifier("data/haarcascades/haarcascade_frontalface_alt.xml")

        if (face_cascade.empty()) {
            println("--(!)Error loading A\n")
        } else {
            println("Face classifier loooaaaaaded up")
        }
    }

    fun detect(inputframe: Mat) {
        val mGrey = Mat()
        val faces = MatOfRect()
        inputframe.copyTo(mGrey)
        cvtColor(inputframe, mGrey, COLOR_BGR2GRAY)
        equalizeHist(mGrey, mGrey)
        face_cascade.detectMultiScale(mGrey, faces)
        val maskResized = Mat()
        for (rect in faces.toArray()) {
            resize(mask, maskResized, Size(rect.width.toDouble(), rect.height.toDouble()))
            val adjusty = (rect.y - rect.width * adjust).toInt()
            try {
                drawTransparency(inputframe, maskResized, rect.x, adjusty)
            } catch (e:Exception) {
                //
            }
        }
    }

    companion object {

        fun drawTransparency(frame: Mat, transp: Mat, xPos: Int, yPos: Int) {
            val layers = ArrayList<Mat>()
            Core.split(transp, layers)
            val mask = layers.removeAt(3)
            Core.merge(layers, transp)
            val submat = frame.submat(yPos, yPos + transp.rows(), xPos, xPos + transp.cols())
            transp.copyTo(submat, mask)
        }
    }
}

object FaceDetectionWithImageOverlay {

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        init()
        val window_name = "Capture - Face detection"
        val frame = JFrame(window_name)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(400, 400)
        val _mask = if (args.size > 0) args[0] else "data/lupin_head.jpg"
        val adjust = if (args.size > 1) java.lang.Double.parseDouble(args[1]) else 0.2
        val my_processor = processor(_mask, adjust)
        val my_panel = MyPanel()
        frame.contentPane = my_panel
        frame.isVisible = true
        val webcam_image = Mat()
        val capture = VideoCapture()
        capture.open(0)

        if (capture.isOpened) {

            capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, 600.0)
            capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, 800.0)

            while (capture.read(webcam_image)) {

                val sz = Size(600.0, 400.0)
                resize(webcam_image, webcam_image, sz)
                frame.setSize(2 * webcam_image.width() + 40, 2 * webcam_image.height() + 60)
                my_processor.detect(webcam_image)
                my_panel.image = HighGui.toBufferedImage(webcam_image) as BufferedImage
                my_panel.repaint()
            }
        }
        return
    }
}