package sample

import org.opencv.core.*
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Imgproc.*
import org.opencv.objdetect.CascadeClassifier
import org.opencv.videoio.VideoCapture
import org.opencv.videoio.Videoio.CAP_PROP_FRAME_HEIGHT
import org.opencv.videoio.Videoio.CAP_PROP_FRAME_WIDTH
import org.scijava.nativelib.NativeLoader
import origami.Origami
import origami.Origami.*
import java.awt.FlowLayout
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.IOException
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel

object DetectFace {

    internal var frame: JFrame? = null
    internal var lbl: JLabel? = null
    internal lateinit var icon: ImageIcon

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        init()

        val cascadeFaceClassifier = CascadeClassifier(
                "data/haarcascades/haarcascade_frontalface_default.xml")
        val cascadeEyeClassifier = CascadeClassifier(
                "data/haarcascades/haarcascade_eye.xml")
        val cascadeNoseClassifier = CascadeClassifier(
                "data/haarcascades/haarcascade_mcs_nose.xml")

        val videoDevice = VideoCapture()
        videoDevice.set(CAP_PROP_FRAME_WIDTH, 640.0)
        videoDevice.set(CAP_PROP_FRAME_HEIGHT, 480.0)

        videoDevice.open(0)

        if (videoDevice.isOpened) {

            while (true) {
                val frameCapture = Mat()
                videoDevice.read(frameCapture)


                val faces = MatOfRect()
                cascadeFaceClassifier.detectMultiScale(frameCapture, faces)
                for (rect in faces.toArray()) {

                    putText(frameCapture, "Face", Point(rect.x.toDouble(), (rect.y - 5).toDouble()), 1, 2.0, Scalar(0.0, 0.0, 255.0))
                    rectangle(frameCapture, Point(rect.x.toDouble(), rect.y.toDouble()), Point((rect.x + rect.width).toDouble(), (rect.y + rect.height).toDouble()),
                            Scalar(0.0, 100.0, 0.0), 3)
                }

                val eyes = MatOfRect()
                cascadeEyeClassifier.detectMultiScale(frameCapture, eyes)
                for (rect in eyes.toArray()) {
                    putText(frameCapture, "Eye", Point(rect.x.toDouble(), (rect.y - 5).toDouble()), 1, 2.0, Scalar(0.0, 0.0, 255.0))
                    rectangle(frameCapture, Point(rect.x.toDouble(), rect.y.toDouble()), Point((rect.x + rect.width).toDouble(), (rect.y + rect.height).toDouble()),
                            Scalar(200.0, 200.0, 100.0), 2)
                }

                val nose = MatOfRect()
                cascadeNoseClassifier.detectMultiScale(frameCapture, nose)
                for (rect in nose.toArray()) {

                    putText(frameCapture, "Nose", Point(rect.x.toDouble(), (rect.y - 5).toDouble()), 1, 2.0, Scalar(0.0, 0.0, 255.0))
                    rectangle(frameCapture, Point(rect.x.toDouble(), rect.y.toDouble()), Point((rect.x + rect.width).toDouble(), (rect.y + rect.height).toDouble()),
                            Scalar(50.0, 255.0, 50.0), 2)
                }

                PushImage(ConvertMat2Image(frameCapture))
//                println(String.format("%s FACES %s EYE %s NOSE detected.", faces.toArray().size, eyes.toArray().size, nose.toArray().size))
            }
        }
    }

    private fun ConvertMat2Image(kameraVerisi: Mat): BufferedImage? {


        val byteMatVerisi = MatOfByte()
        Imgcodecs.imencode(".jpg", kameraVerisi, byteMatVerisi)
        val byteArray = byteMatVerisi.toArray()
        var goruntu: BufferedImage?
        try {
            val `in` = ByteArrayInputStream(byteArray)
            goruntu = ImageIO.read(`in`)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return goruntu
    }


    fun PencereHazirla() {
        frame = JFrame()
        frame!!.layout = FlowLayout()
        frame!!.setSize(700, 600)
        frame!!.isVisible = true
        frame!!.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }

    fun PushImage(img2: Image?) {
        if (frame == null)
            PencereHazirla()
        if (lbl != null)
            frame!!.remove(lbl)
        icon = ImageIcon(img2!!)
        lbl = JLabel()
        lbl!!.icon = icon
        frame!!.add(lbl)
        frame!!.revalidate()
    }
}