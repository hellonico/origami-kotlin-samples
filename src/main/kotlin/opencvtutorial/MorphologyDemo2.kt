package opencvtutorial

import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Size
import org.opencv.highgui.HighGui
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc.*
import origami.Origami
import java.awt.BorderLayout
import java.awt.Container
import java.awt.Image
import javax.swing.*

class MorphologyDemo2(args: Array<String>) {
    private val matImgSrc: Mat
    private val matImgDst = Mat()
    private var morphOpType = MORPH_OPEN
    private var elementType = CV_SHAPE_RECT
    private var kernelSize = 0
    private val frame: JFrame
    private var imgLabel: JLabel? = null
    private fun addComponentsToPane(pane: Container, img: Image) {
        if (pane.layout !is BorderLayout) {
            pane.add(JLabel("Container doesn't use BorderLayout!"))
            return
        }
        val sliderPanel = JPanel()
        sliderPanel.layout = BoxLayout(sliderPanel, BoxLayout.PAGE_AXIS)
        val morphOpBox = JComboBox(MORPH_OP)
        morphOpBox.addActionListener { e ->
            val cb = e.source as JComboBox<String>
            morphOpType = MORPH_OP_TYPE[cb.selectedIndex]
            update()
        }
        sliderPanel.add(morphOpBox)
        val elementTypeBox = JComboBox(ELEMENT_TYPE)
        elementTypeBox.addActionListener { e ->
            val cb = e.source as JComboBox<String>
            if (cb.selectedIndex == 0) {
                elementType = CV_SHAPE_RECT
            } else if (cb.selectedIndex == 1) {
                elementType = CV_SHAPE_CROSS
            } else if (cb.selectedIndex == 2) {
                elementType = CV_SHAPE_ELLIPSE
            }
            update()
        }
        sliderPanel.add(elementTypeBox)
        sliderPanel.add(JLabel("Kernel size: 2n + 1"))
        val slider = JSlider(0, MAX_KERNEL_SIZE, 0)
        slider.majorTickSpacing = 5
        slider.minorTickSpacing = 5
        slider.paintTicks = true
        slider.paintLabels = true
        slider.addChangeListener { e ->
            val source = e.source as JSlider
            kernelSize = source.value
            update()
        }
        sliderPanel.add(slider)
        pane.add(sliderPanel, BorderLayout.PAGE_START)
        imgLabel = JLabel(ImageIcon(img))
        pane.add(imgLabel, BorderLayout.CENTER)
    }

    private fun update() {
        val element = getStructuringElement(
            elementType, Size((2 * kernelSize + 1).toDouble(), (2 * kernelSize + 1).toDouble()),
            Point(kernelSize.toDouble(), kernelSize.toDouble())
        )
        morphologyEx(matImgSrc, matImgDst, morphOpType, element)
        val img = HighGui.toBufferedImage(matImgDst)
        imgLabel!!.icon = ImageIcon(img)
        frame.repaint()
    }

    private val MORPH_OP = arrayOf("Opening", "Closing", "Gradient", "Top Hat", "Black Hat")
    private val MORPH_OP_TYPE = intArrayOf(
        MORPH_OPEN, MORPH_CLOSE, MORPH_GRADIENT,
        MORPH_TOPHAT, MORPH_BLACKHAT
    )
    private val ELEMENT_TYPE = arrayOf("Rectangle", "Cross", "Ellipse")
    private val MAX_KERNEL_SIZE = 21

    init {
        val imagePath = if (args.size > 0) args[0] else "data/geeksforgeeks/cat_inpainted.png"
        matImgSrc = Imgcodecs.imread(imagePath)
        if (matImgSrc.empty()) {
            println("Empty image: $imagePath")
            System.exit(0)
        }

        // Create and set up the window.
        frame = JFrame("Morphology Transformations demo")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        // Set up the content pane.
        val img = HighGui.toBufferedImage(matImgSrc)
        addComponentsToPane(frame.contentPane, img)
        // Use the content pane's default BorderLayout. No need for
        // setLayout(new BorderLayout());
        // Display the window.
        frame.pack()
        frame.isVisible = true
    }
}

fun main(args: Array<String>) {
    Origami.init()
    // Schedule a job for the event dispatch thread:
    // creating and showing this application's GUI.
    SwingUtilities.invokeLater { MorphologyDemo2(args) }
}

