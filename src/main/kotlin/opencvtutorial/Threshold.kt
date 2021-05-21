package opencvtutorial

import org.opencv.core.Mat
import org.opencv.highgui.HighGui
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import origami.Origami
import java.awt.BorderLayout
import java.awt.Container
import java.awt.Image
import javax.swing.*

class Threshold(args: Array<String>) {
    private var thresholdValue = 0
    private var thresholdType = 3
    private val src: Mat
    private val srcGray = Mat()
    private val dst = Mat()
    private val frame: JFrame
    private var imgLabel: JLabel? = null
    private fun addComponentsToPane(pane: Container, img: Image) {
        if (pane.layout !is BorderLayout) {
            pane.add(JLabel("Container doesn't use BorderLayout!"))
            return
        }
        val sliderPanel = JPanel()
        sliderPanel.layout = BoxLayout(sliderPanel, BoxLayout.PAGE_AXIS)

        // ! [trackbar]
        sliderPanel.add(JLabel(TRACKBAR_TYPE))
        // Create Trackbar to choose type of opencvtutorial.Threshold
        val sliderThreshType = JSlider(0, MAX_TYPE, thresholdType)
        sliderThreshType.majorTickSpacing = 1
        sliderThreshType.minorTickSpacing = 1
        sliderThreshType.paintTicks = true
        sliderThreshType.paintLabels = true
        sliderPanel.add(sliderThreshType)
        sliderPanel.add(JLabel(TRACKBAR_VALUE))
        // Create Trackbar to choose opencvtutorial.Threshold value
        val sliderThreshValue = JSlider(0, MAX_VALUE, 0)
        sliderThreshValue.majorTickSpacing = 50
        sliderThreshValue.minorTickSpacing = 10
        sliderThreshValue.paintTicks = true
        sliderThreshValue.paintLabels = true
        sliderPanel.add(sliderThreshValue)
        // ! [trackbar]

        // ! [on_trackbar]
        sliderThreshType.addChangeListener { e ->
            val source = e.source as JSlider
            thresholdType = source.value
            update()
        }
        sliderThreshValue.addChangeListener { e ->
            val source = e.source as JSlider
            thresholdValue = source.value
            update()
        }
        // ! [on_trackbar]
        pane.add(sliderPanel, BorderLayout.PAGE_START)
        imgLabel = JLabel(ImageIcon(img))
        pane.add(imgLabel, BorderLayout.CENTER)
    }

    // ! [Threshold_Demo]
    private fun update() {
        Imgproc.threshold(srcGray, dst, thresholdValue.toDouble(), MAX_BINARY_VALUE.toDouble(), thresholdType)
        val img = HighGui.toBufferedImage(dst)
        imgLabel!!.icon = ImageIcon(img)
        frame.repaint()
    }

    companion object {
        private const val MAX_VALUE = 255
        private const val MAX_TYPE = 4
        private const val MAX_BINARY_VALUE = 255
        private const val WINDOW_NAME = "opencvtutorial.Threshold Demo"
        private const val TRACKBAR_TYPE = ("<html><body>Type: <br> 0: Binary <br> "
                + "1: Binary Inverted <br> 2: Truncate <br> " + "3: To Zero <br> 4: To Zero Inverted</body></html>")
        private const val TRACKBAR_VALUE = "Value"

        @JvmStatic
        fun main(args: Array<String>) {

            // Schedule a job for the event dispatch thread:
            // creating and showing this application's GUI.
            SwingUtilities.invokeLater { Threshold(args) }
        }

        // ! [Threshold_Demo]
        init {
            Origami.init()
        }
    }

    init {
        // ! [load]
        var imagePath = "data/stuff.jpg"
        if (args.size > 0) {
            imagePath = args[0]
        }
        // Load an image
        src = Imgcodecs.imread(imagePath)
        if (src.empty()) {
            println("Empty image: $imagePath")
            System.exit(0)
        }
        // Convert the image to Gray
        Imgproc.cvtColor(src, srcGray, Imgproc.COLOR_BGR2GRAY)
        // ! [load]

        // ! [window]
        // Create and set up the window.
        frame = JFrame(WINDOW_NAME)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        // Set up the content pane.
        val img = HighGui.toBufferedImage(srcGray)
        addComponentsToPane(frame.contentPane, img)
        // Use the content pane's default BorderLayout. No need for
        // setLayout(new BorderLayout());
        // Display the window.
        frame.pack()
        frame.isVisible = true
        // ! [window]
    }
}