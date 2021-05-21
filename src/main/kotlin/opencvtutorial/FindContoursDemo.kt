package opencvtutorial

import javax.swing.JFrame
import javax.swing.JLabel
import java.awt.BorderLayout
import javax.swing.JPanel
import javax.swing.BoxLayout
import javax.swing.JSlider
import opencvtutorial.FindContours
import org.opencv.core.*
import javax.swing.event.ChangeEvent
import javax.swing.ImageIcon
import org.opencv.highgui.HighGui
import org.opencv.imgproc.Imgproc
import org.opencv.imgcodecs.Imgcodecs
import java.awt.Container
import java.awt.Image
import java.lang.Exception
import kotlin.jvm.JvmStatic
import javax.swing.SwingUtilities
import java.lang.Runnable
import java.util.*

internal class FindContours(args: Array<String>) {
    private val srcGray = Mat()
    private val frame: JFrame
    private var imgSrcLabel: JLabel? = null
    private var imgContoursLabel: JLabel? = null
    private var threshold = 100
    private val rng = Random(12345)
    private fun addComponentsToPane(pane: Container, img: Image) {
        if (pane.layout !is BorderLayout) {
            pane.add(JLabel("Container doesn't use BorderLayout!"))
            return
        }
        val sliderPanel = JPanel()
        sliderPanel.layout = BoxLayout(sliderPanel, BoxLayout.PAGE_AXIS)
        sliderPanel.add(JLabel("Canny threshold: "))
        val slider = JSlider(0, MAX_THRESHOLD, threshold)
        slider.majorTickSpacing = 20
        slider.minorTickSpacing = 10
        slider.paintTicks = true
        slider.paintLabels = true
        slider.addChangeListener { e ->
            val source = e.source as JSlider
            threshold = source.value
            update()
        }
        sliderPanel.add(slider)
        pane.add(sliderPanel, BorderLayout.PAGE_START)
        val imgPanel = JPanel()
        imgSrcLabel = JLabel(ImageIcon(img))
        imgPanel.add(imgSrcLabel)
        val blackImg = Mat.zeros(srcGray.size(), CvType.CV_8U)
        imgContoursLabel = JLabel(ImageIcon(HighGui.toBufferedImage(blackImg)))
        imgPanel.add(imgContoursLabel)
        pane.add(imgPanel, BorderLayout.CENTER)
    }

    private fun update() {
        /// Detect edges using Canny
        val cannyOutput = Mat()
        Imgproc.Canny(srcGray, cannyOutput, threshold.toDouble(), (threshold * 2).toDouble())

        /// Find contours
        val contours: List<MatOfPoint> = ArrayList()
        val hierarchy = Mat()
        Imgproc.findContours(cannyOutput, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE)

        /// Draw contours
        val drawing = Mat.zeros(cannyOutput.size(), CvType.CV_8UC3)
        for (i in contours.indices) {
            val color = Scalar(
                rng.nextInt(256).toDouble(), rng.nextInt(256).toDouble(), rng.nextInt(256).toDouble()
            )
            Imgproc.drawContours(drawing, contours, i, color, 2, Imgproc.LINE_8, hierarchy, 0, Point())
        }
        imgContoursLabel!!.icon = ImageIcon(HighGui.toBufferedImage(drawing))
        frame.repaint()
    }

    companion object {
        private const val MAX_THRESHOLD = 255
    }

    init {
        /// Load source image
        val filename = if (args.size > 0) args[0] else "data/HappyFish.jpg"
        val src = Imgcodecs.imread(filename)
        if (src.empty()) {
            System.err.println("Cannot read image: $filename")
            System.exit(0)
        }

        /// Convert image to gray and blur it
        Imgproc.cvtColor(src, srcGray, Imgproc.COLOR_BGR2GRAY)
        Imgproc.blur(srcGray, srcGray, Size(3.0, 3.0))

        // Create and set up the window.
        frame = JFrame("Finding contours in your image demo")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        // Set up the content pane.
        val img = HighGui.toBufferedImage(src)
        addComponentsToPane(frame.contentPane, img)
        // Use the content pane's default BorderLayout. No need for
        // setLayout(new BorderLayout());
        // Display the window.
        frame.pack()
        frame.isVisible = true
        update()
    }
}

object FindContoursDemo {
    @JvmStatic
    fun main(args: Array<String>) {

        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater { FindContours(args) }
    }

    init {
        try {
            // Load the native OpenCV library
        } catch (e: Exception) {
        }
    }
}