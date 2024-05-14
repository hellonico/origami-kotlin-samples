package sample

import org.opencv.core.*
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.BufferedImage

import javax.swing.JFrame

import org.opencv.highgui.HighGui
import org.opencv.imgproc.Imgproc.*
import org.opencv.videoio.VideoCapture
import org.opencv.videoio.Videoio
import org.scijava.nativelib.NativeLoader.*
import origami.Origami
import origami.Origami.*

class VideoFrame : JFrame() {
    lateinit var image: BufferedImage

    override fun paint(g: Graphics) {
        g.drawImage(image, 0, 0, this)
    }

    init {
        this.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        this.extendedState = JFrame.MAXIMIZED_BOTH
        this.isUndecorated = true
        this.isVisible = true
        this.pack()
    }

    companion object {

        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            init()
            val t = VideoFrame()
            val videoFile = args.getOrElse(0, {_-> "data/lexus.mpg"})
            val camera = VideoCapture()
            camera.open(videoFile);
            val frame = Mat()
            val di = getDimensions(camera, t, true)
            if (!camera.isOpened) {
                throw RuntimeException("Cannot read file")
            } else {
                while (camera.grab()) {
                    camera.read(frame)
                    val dst = Mat()
                    resize(frame, dst, Size(di.getWidth().toInt().toDouble(), (50 + di.getHeight().toInt()).toDouble()))
                    putText(dst, "FPS:${camera.get(Videoio.CAP_PROP_FPS)}", Point(50.0,50.0) , 0, 2.0, Scalar(255.0, 255.0, 255.0))
                    t.image = HighGui.toBufferedImage(dst) as BufferedImage
                    t.repaint()
                }
                camera.release()
            }

        }

        private fun getDimensions(camera: VideoCapture, t: VideoFrame, fullscreen: Boolean): Dimension {
            if (fullscreen) {
                val d = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
                        .defaultScreenDevice
                d.fullScreenWindow = t
                return d.fullScreenWindow.size
            } else {
                return Dimension(camera.get(Videoio.CAP_PROP_FRAME_WIDTH).toInt(),
                        camera.get(Videoio.CAP_PROP_FRAME_HEIGHT).toInt())
            }

        }
    }

}