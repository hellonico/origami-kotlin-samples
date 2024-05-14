package sample

import org.opencv.core.*
import org.opencv.highgui.HighGui
import org.opencv.imgproc.Imgproc.*
import org.opencv.objdetect.CascadeClassifier
import org.opencv.videoio.VideoCapture
import org.scijava.nativelib.NativeLoader
import origami.Origami
import origami.Origami.*
import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.JPanel

internal class MyPanel : JPanel() {
    var image: BufferedImage? = null

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        if (this.image == null)
            return
        g.drawImage(this.image, 10, 10, 2 * this.image!!.width, 2 * this.image!!.height, null)
        // g.drawString("This is my custom Panel!",10,20);
    }

    companion object {
        private val serialVersionUID = 1L
    }
}

internal class MyProcessor {
    private val face_cascade: CascadeClassifier

    init {
        face_cascade = CascadeClassifier("data/haarcascades/haarcascade_frontalface_alt.xml")
    }

    fun detect(inputframe: Mat): Mat {
        val startTime = System.nanoTime()
        val mRgba = Mat()
        val mGrey = Mat()
        val faces = MatOfRect()
        inputframe.copyTo(mRgba)
        inputframe.copyTo(mGrey)
        cvtColor(mRgba, mGrey, COLOR_BGR2GRAY)
        equalizeHist(mGrey, mGrey)
        face_cascade.detectMultiScale(mGrey, faces)
        val endTime = System.nanoTime()
        for (rect in faces.toArray()) {
            val center = Point(rect.x + rect.width * 0.5, rect.y + rect.height * 0.5)
            ellipse(mRgba, center, Size(rect.width * 0.5, rect.height * 0.5), 0.0, 0.0, 360.0,
                    Scalar(255.0, 0.0, 255.0), 4, 8, 0)
        }
        return mRgba
    }
}

object WebcamFaceDetection {

    @JvmStatic
    fun main(arg: Array<String>) {
        init()
        val frame = JFrame("Capture - Face detection")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(400, 300)
        val myProcessor = MyProcessor()
        val myPanel = MyPanel()
        frame.contentPane = myPanel
        frame.isVisible = true
        var webcamImage = Mat()
        val capture = VideoCapture()
        capture.open(0)

        if (capture.isOpened) {

            while (true) {
                capture.read(webcamImage)
                val sz = Size(300.0, 180.0)
                resize(webcamImage, webcamImage, sz)
                frame.setSize(2 * webcamImage.width() + 40, 2 * webcamImage.height() + 60)
                webcamImage = myProcessor.detect(webcamImage)
                myPanel.image = HighGui.toBufferedImage(webcamImage) as BufferedImage?
                myPanel.repaint()
            }
        }
        return
    }
}