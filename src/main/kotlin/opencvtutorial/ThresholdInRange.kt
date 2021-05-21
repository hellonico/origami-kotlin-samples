package opencvtutorial

import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Scalar
import org.opencv.highgui.HighGui
import org.opencv.imgproc.Imgproc
import org.opencv.videoio.VideoCapture
import origami.Origami
import java.awt.BorderLayout
import java.awt.Container
import java.awt.Image
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

/**
 * https://docs.opencv.org/master/da/d97/tutorial_threshold_inRange.html
 */
class ThresholdInRange(args: Array<String>) {
    private var sliderLowH: JSlider? = null
    private var sliderHighH: JSlider? = null
    private var sliderLowS: JSlider? = null
    private var sliderHighS: JSlider? = null
    private var sliderLowV: JSlider? = null
    private var sliderHighV: JSlider? = null
    private val cap: VideoCapture
    private val matFrame = Mat()
    private val frame: JFrame
    private var imgCaptureLabel: JLabel? = null
    private var imgDetectionLabel: JLabel? = null
    private val captureTask: CaptureTask

    private inner class CaptureTask : SwingWorker<Void?, Mat?>() {
        override fun doInBackground(): Void? {
            val matFrame = Mat()
            while (!isCancelled) {
                if (!cap.read(matFrame)) {
                    break
                }
                publish(matFrame.clone())
            }
            return null
        }

        override fun process(frames: List<Mat?>) {
            val frame = frames[frames.size - 1]
            val frameHSV = Mat()
            Imgproc.cvtColor(frame, frameHSV, Imgproc.COLOR_BGR2HSV)
            val thresh = Mat()
            Core.inRange(
                frameHSV, Scalar(
                    sliderLowH!!.value.toDouble(), sliderLowS!!.value.toDouble(), sliderLowV!!.value.toDouble()
                ),
                Scalar(
                    sliderHighH!!.value.toDouble(), sliderHighS!!.value.toDouble(), sliderHighV!!.value.toDouble()
                ), thresh
            )
            update(frame, thresh)
        }
    }

    private fun addComponentsToPane(pane: Container, img: Image) {
        if (pane.layout !is BorderLayout) {
            pane.add(JLabel("Container doesn't use BorderLayout!"))
            return
        }
        val sliderPanel = JPanel()
        sliderPanel.layout = BoxLayout(sliderPanel, BoxLayout.PAGE_AXIS)
        sliderPanel.add(JLabel(LOW_H_NAME))
        sliderLowH = JSlider(0, MAX_VALUE_H, 0)
        sliderLowH!!.majorTickSpacing = 50
        sliderLowH!!.minorTickSpacing = 10
        sliderLowH!!.paintTicks = true
        sliderLowH!!.paintLabels = true
        sliderPanel.add(sliderLowH)
        sliderPanel.add(JLabel(HIGH_H_NAME))
        sliderHighH = JSlider(0, MAX_VALUE_H, MAX_VALUE_H)
        sliderHighH!!.majorTickSpacing = 50
        sliderHighH!!.minorTickSpacing = 10
        sliderHighH!!.paintTicks = true
        sliderHighH!!.paintLabels = true
        sliderPanel.add(sliderHighH)
        sliderPanel.add(JLabel(LOW_S_NAME))
        sliderLowS = JSlider(0, MAX_VALUE, 0)
        sliderLowS!!.majorTickSpacing = 50
        sliderLowS!!.minorTickSpacing = 10
        sliderLowS!!.paintTicks = true
        sliderLowS!!.paintLabels = true
        sliderPanel.add(sliderLowS)
        sliderPanel.add(JLabel(HIGH_S_NAME))
        sliderHighS = JSlider(0, MAX_VALUE, MAX_VALUE)
        sliderHighS!!.majorTickSpacing = 50
        sliderHighS!!.minorTickSpacing = 10
        sliderHighS!!.paintTicks = true
        sliderHighS!!.paintLabels = true
        sliderPanel.add(sliderHighS)
        sliderPanel.add(JLabel(LOW_V_NAME))
        sliderLowV = JSlider(0, MAX_VALUE, 0)
        sliderLowV!!.majorTickSpacing = 50
        sliderLowV!!.minorTickSpacing = 10
        sliderLowV!!.paintTicks = true
        sliderLowV!!.paintLabels = true
        sliderPanel.add(sliderLowV)
        sliderPanel.add(JLabel(HIGH_V_NAME))
        sliderHighV = JSlider(0, MAX_VALUE, MAX_VALUE)
        sliderHighV!!.majorTickSpacing = 50
        sliderHighV!!.minorTickSpacing = 10
        sliderHighV!!.paintTicks = true
        sliderHighV!!.paintLabels = true
        sliderPanel.add(sliderHighV)
        sliderLowH!!.addChangeListener { e ->
            val source = e.source as JSlider
            val valH = Math.min(sliderHighH!!.value - 1, source.value)
            sliderLowH!!.value = valH
        }
        sliderHighH!!.addChangeListener { e ->
            val source = e.source as JSlider
            val valH = Math.max(source.value, sliderLowH!!.value + 1)
            sliderHighH!!.value = valH
        }
        sliderLowS!!.addChangeListener { e ->
            val source = e.source as JSlider
            val valS = Math.min(sliderHighS!!.value - 1, source.value)
            sliderLowS!!.value = valS
        }
        sliderHighS!!.addChangeListener { e ->
            val source = e.source as JSlider
            val valS = Math.max(source.value, sliderLowS!!.value + 1)
            sliderHighS!!.value = valS
        }
        sliderLowV!!.addChangeListener { e ->
            val source = e.source as JSlider
            val valV = Math.min(sliderHighV!!.value - 1, source.value)
            sliderLowV!!.value = valV
        }
        sliderHighV!!.addChangeListener { e ->
            val source = e.source as JSlider
            val valV = Math.max(source.value, sliderLowV!!.value + 1)
            sliderHighV!!.value = valV
        }
        pane.add(sliderPanel, BorderLayout.PAGE_START)
        val framePanel = JPanel()
        imgCaptureLabel = JLabel(ImageIcon(img))
        imgDetectionLabel = JLabel(ImageIcon(img))
        framePanel.add(imgDetectionLabel)
        pane.add(framePanel, BorderLayout.CENTER)
    }

    private fun update(imgCapture: Mat?, imgThresh: Mat) {
        imgCaptureLabel!!.icon = ImageIcon(HighGui.toBufferedImage(imgCapture))
        imgDetectionLabel!!.icon = ImageIcon(HighGui.toBufferedImage(imgThresh))
        frame.repaint()
    }

    companion object {
        private const val MAX_VALUE = 255
        private const val MAX_VALUE_H = 360 / 2
        private const val WINDOW_NAME = "Thresholding Operations using inRange demo"
        private const val LOW_H_NAME = "Low H"
        private const val LOW_S_NAME = "Low S"
        private const val LOW_V_NAME = "Low V"
        private const val HIGH_H_NAME = "High H"
        private const val HIGH_S_NAME = "High S"
        private const val HIGH_V_NAME = "High V"
        @JvmStatic
        fun main(args: Array<String>) {
            Origami.init()
            SwingUtilities.invokeLater { ThresholdInRange(args) }
        }
    }

    init {
        var cameraDevice = 0
        if (args.size > 0) {
            cameraDevice = args[0].toInt()
        }
        cap = VideoCapture()
        captureTask = CaptureTask()
        cap.open(cameraDevice)
        if (!cap.isOpened) {
            System.err.println("Cannot open camera: $cameraDevice")
            System.exit(0)
        }
        if (!cap.read(matFrame)) {
            System.err.println("Cannot read camera stream.")
            System.exit(0)
        }
        // Create and set up the window.
        frame = JFrame(WINDOW_NAME)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(windowEvent: WindowEvent) {
                captureTask.cancel(true)
            }
        })
        // Set up the content pane.
        val img = HighGui.toBufferedImage(matFrame)
        addComponentsToPane(frame.contentPane, img)
        // Use the content pane's default BorderLayout. No need for
        // setLayout(new BorderLayout());
        // Display the window.
        frame.pack()
        frame.isVisible = true

        captureTask.execute()
    }
}