## backgroundModel.kt


fun main(args: Array<String>) {
    Origami.init()
    val foreground = Mat()
    val fg_mask = Mat()
    val MoG = Video.createBackgroundSubtractorMOG2()
    Camera().device(0).filter(Filter()
        {
            MoG.apply(it, fg_mask, 0.1)
            cvtColor(fg_mask, fg_mask, COLOR_GRAY2BGR)
            bitwise_and(it, fg_mask, foreground)
            foreground
        }
    ).run()

}

## hogPeopleDetection.kt


fun main(args: Array<String>) {
    Origami.init()
    val HOG = HOGDescriptor()
    HOG.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector())

    val foundLocations = MatOfRect()
    val foundWeights = MatOfDouble()
    val color = Scalar(255.0, 0.0, 0.0)

    Camera().device(0).filter(Filter() {
        HOG.detectMultiScale(
            it, foundLocations, foundWeights, 0.0, Size(8.0, 8.0), Size(32.0, 32.0),
            1.05, 8.0, false
        )
        drawLocations(foundLocations, it, color)
    }).run()
}

private fun drawLocations(foundLocations: MatOfRect, it: Mat?, color: Scalar): Mat? {
    val rectangles = foundLocations.toList()
    for (i in rectangles.indices) {
        rectangle(
            it, Point(rectangles[i].x.toDouble(), rectangles[i].y.toDouble()),
            Point(
                (rectangles[i].x + rectangles[i].width).toDouble(),
                (rectangles[i].y + rectangles[i].height).toDouble()
            ),
            color, 2, 1, 0
        )
    }
    return it
}

## ApplyingBoxFilter.kt


fun createKernelOfSize(kernelSize: Int): Mat {
    val kernel = Mat.ones(kernelSize, kernelSize, CvType.CV_32F)
    for (i in 0 until kernel.rows()) {
        for (j in 0 until kernel.cols()) {
            val m = kernel[i, j]
            for (k in m.indices) {
                m[k] = m[k] / (kernelSize * kernelSize)
            }
            kernel.put(i, j, *m)
        }
    }
    return kernel
}

fun main(args: Array<String>) {
    Origami.init()
    val source = Imgcodecs.imread("data/dip/grayscale.jpg", Imgcodecs.IMREAD_GRAYSCALE)
    val destination = Mat(source.rows(), source.cols(), source.type())
    val kernel5 = createKernelOfSize(5)
    Imgproc.filter2D(source, destination, -1, kernel5)
    Imgcodecs.imwrite("boxfilterKernel5.jpg", destination)
    val kernel9 = createKernelOfSize(9)
    Imgproc.filter2D(source, destination, -1, kernel9)
    Imgcodecs.imwrite("boxfilterKernel9.jpg", destination)
}

## GaussianFilter.kt


fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    val destination = Mat(source.rows(), source.cols(), source.type())
    GaussianBlur(source, destination, Size(11.0, 11.0), 0.0)
    imwrite("gaussianblur1.jpg", destination)
    GaussianBlur(source, destination, Size(45.0, 45.0), 0.0)
    imwrite("gaussianblur45.jpg", destination)
}

## ApplyingWatermarkWithROI.kt


fun main(args: Array<String>) {
    Origami.init()
    val source = Imgcodecs.imread("data/dip/digital_image_processing.jpg", Imgcodecs.IMREAD_COLOR)
    val waterMark = Imgcodecs.imread("data/dip/watermark.jpg", Imgcodecs.IMREAD_COLOR)
    val ROI = Rect(20, 20, waterMark.cols(), waterMark.rows())
    Core.addWeighted(source.submat(ROI), 0.8, waterMark, 0.2, 1.0, source.submat(ROI))
    Imgcodecs.imwrite("watermarkedROI.jpg", source)
}

## EnhanceImageSharpness.kt


fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    val destination = Mat(source.rows(), source.cols(), source.type())
    GaussianBlur(source, destination, Size(1.0, 1.0), 10.0)
    addWeighted(source, 1.5, destination, -0.5, 0.0, destination)
    imwrite("sharp.jpg", destination)
}

## Sobel.kt


fun main(args: Array<String>) {
    init()
    val kernelSize = 3
    val source = imread("data/dip/grayscale.jpg", IMREAD_GRAYSCALE)
    val destination = Mat(source.rows(), source.cols(), source.type())
    val kernel: Mat = object : Mat(kernelSize, kernelSize, CV_32F) {
        init {
            put(0, 0, -1.0)
            put(0, 1, 0.0)
            put(0, 2, 1.0)

            put(1, 0, - 2.0)
            put(1, 1, 0.0)
            put(1, 2, 2.0)

            put(2, 0, -1.0)
            put(2, 1, 0.0)
            put(2, 2, 1.0)
        }
    }

    filter2D(source, destination, -1, kernel)
    imwrite("sobel.jpg", destination)
}

## EnhanceImageBrightness.kt


fun main(args: Array<String>) {
    Origami.init()
    var alpha = 2.0
    var beta = 50.0
    val source = Imgcodecs.imread("data/dip/digital_image_processing.jpg", Imgcodecs.IMREAD_COLOR)
    val destination = Mat(source.rows(), source.cols(), source.type())
    source.convertTo(destination, -1, alpha, beta)
    Imgcodecs.imwrite("brightWithAlpha2Beta50.jpg", destination)
}

## Prewitt.kt


fun main(args: Array<String>) {
    init()
    val kernelSize = 3
    val source = imread("data/dip/grayscale.jpg", IMREAD_GRAYSCALE)
    val destination = Mat(source.rows(), source.cols(), source.type())
    val kernel: Mat = object : Mat(kernelSize, kernelSize, CV_32F) {
        init {
            put(0, 0, -1.0)
            put(0, 1, 0.0)
            put(0, 2, 1.0)

            put(1, 0, -1.0)
            put(1, 1, 0.0)
            put(1, 2, 1.0)

            put(2, 0, -1.0)
            put(2, 1, 0.0)
            put(2, 2, 1.0)
        }
    }

    filter2D(source, destination, -1, kernel)
    imwrite("prewitt.jpg", destination)
}

## ZoomingEffect.kt


fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/grayscale.jpg", IMREAD_GRAYSCALE)
    val zoomingFactor = 3
    val destination = Mat(source.rows(), source.cols(), source.type())
    resize(
        source,
        destination,
        destination.size(),
        zoomingFactor.toDouble(),
        zoomingFactor.toDouble(),
        INTER_LINEAR
    )
    imwrite("zoomed2.jpg", destination)
}

## Kirsch.kt


fun main(args: Array<String>) {
    init()
    val kernelSize = 3
    val source = imread("data/dip/grayscale.jpg", IMREAD_GRAYSCALE)
    val destination = Mat(source.rows(), source.cols(), source.type())
    val kernel: Mat = object : Mat(kernelSize, kernelSize, CV_32F) {
        init {
            put(0, 0, -3.0)
            put(0, 1, -3.0)
            put(0, 2, -3.0)

            put(1, 0, -3.0)
            put(1, 1, -3.0)
            put(1, 2, -3.0)

            put(2, 0, 5.0)
            put(2, 1, 5.0)
            put(2, 2, 5.0)
        }
    }

    filter2D(source, destination, -1, kernel)
    imwrite("kirsch.jpg", destination)
}

## Pixelize.kt


/**
 */
fun main(args: Array<String>) {
    init()

    val (source, temp,target) = listOf(imread("data/bear.png"), Mat(),Mat())
     val (w, h) = listOf(16.0, 16.0)

    resize(source, temp, Size(w, h), 1.0,1.0, INTER_LINEAR)
    resize(temp, target, source.size(), 1.0,1.0,INTER_NEAREST)

    imwrite("pixelized.jpg", target)
}

## ImageShapeConversions.kt


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

## BasicThresholding.kt


fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    val destination = Mat(source.rows(), source.cols(), source.type())
    threshold(source, destination, 127.0, 255.0, THRESH_TOZERO)
    imwrite("ThreshZero.jpg", destination)
    threshold(source, destination, 127.0, 255.0, THRESH_TOZERO_INV)
    imwrite("ThreshZeroInv.jpg", destination)
    threshold(source, destination, 127.0, 255.0, THRESH_BINARY)
    imwrite("ThreshBinary.jpg", destination)
    threshold(source, destination, 127.0, 255.0, THRESH_BINARY_INV)
    imwrite("ThreshBinaryInv.jpg", destination)
}

## EnhanceImageContrast.kt


fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/grayscale.jpg", IMREAD_GRAYSCALE)
    val destination = Mat(source.rows(), source.cols(), source.type())
    equalizeHist(source, destination)
    imwrite("contrast.jpg", destination)
}

## ImagePyramid.kt


fun main(args: Array<String>) {
    Origami.init()
    var source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    val destination1 = Mat(source.rows() * 2, source.cols() * 2, source.type())
    pyrUp(source, destination1, Size((source.cols() * 2).toDouble(), (source.rows() * 2).toDouble()))
    imwrite("pyrUp.jpg", destination1)
    source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    val destination = Mat(source.rows() / 2, source.cols() / 2, source.type())
    pyrDown(source, destination, Size((source.cols() / 2).toDouble(), (source.rows() / 2).toDouble()))
    imwrite("pyrDown.jpg", destination)
}

## ErodingDilating.kt


fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    val destination = Mat(source.rows(), source.cols(), source.type())
    val erosion_size = 5
    val element = getStructuringElement(
        MORPH_RECT,
        Size((2 * erosion_size + 1).toDouble(), (2 * erosion_size + 1).toDouble())
    )
    erode(source, destination, element)
    imwrite("erosion.jpg", destination)
    val dilation_size = 5
    val element1 = getStructuringElement(
        MORPH_RECT,
        Size((2 * dilation_size + 1).toDouble(), (2 * dilation_size + 1).toDouble())
    )
    dilate(source, destination, element1)
    imwrite("dilation.jpg", destination)
}

## AddingBorder.kt



fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/digital_image_processing.jpg")
    val destination = Mat(source.rows(), source.cols(), source.type())
    val top = source.rows() / 20
    val bottom = source.rows() / 20
    val left = source.cols() / 20
    val right = source.cols() / 20
    copyMakeBorder(source, destination, top, bottom, left, right, BORDER_WRAP)
    imwrite("borderWrap.jpg", destination)
    copyMakeBorder(source, destination, top, bottom, left, right, BORDER_REFLECT)
    imwrite("borderReflect.jpg", destination)
    copyMakeBorder(source, destination, top, bottom, left, right, BORDER_REPLICATE)
    imwrite("borderReplicate.jpg", destination)
}

## WeightedAverage.kt


fun main(args: Array<String>) {
    Origami.init()
    val kernelSize = 9
    val source = imread("data/dip/grayscale.jpg", Imgcodecs.IMREAD_GRAYSCALE)
    val destination = Mat(source.rows(), source.cols(), source.type())
    val kernel = Mat.ones(kernelSize, kernelSize, CvType.CV_32F)
    for (i in 0 until kernel.rows()) {
        for (j in 0 until kernel.cols()) {
            val m = kernel[i, j]
            for (k in m.indices) {
                if (i == 1 && j == 1) {
                    m[k] = (10 / 18).toDouble()
                } else {
                    m[k] = m[k] / 18
                }
            }
            kernel.put(i, j, *m)
        }
    }
    filter2D(source, destination, -1, kernel)
    imwrite("weightedaveragefilter.jpg", destination)
}

## Convolution.kt


fun main(args: Array<String>) {
    init()
    val kernelSize = 3
    val source = imread("data/dip/grayscale.jpg", IMREAD_GRAYSCALE)
    val destination = Mat(source.rows(), source.cols(), source.type())
    val kernel: Mat = object : Mat(kernelSize, kernelSize, CV_32F) {
        init {
            put(0, 0, 0.0)
            put(0, 1, 0.0)
            put(0, 2, 0.0)

            put(1, 0, 0.0)
            put(1, 1, 1.0)
            put(1, 2, 0.0)

            put(2, 0, 0.0)
            put(2, 1, 0.0)
            put(2, 2, 0.0)
        }
    }

    filter2D(source, destination, -1, kernel)
    imwrite("understand.jpg", destination)
}

## Laplacian.kt


fun main(args: Array<String>) {
    init()
    val kernelSize = 3
    val source = imread("data/dip/grayscale.jpg", IMREAD_GRAYSCALE)
    val destination = Mat(source.rows(), source.cols(), source.type())
    val kernel: Mat = object : Mat(kernelSize, kernelSize, CV_32F) {
        init {
            put(0, 0, 0.0)
            put(0, 1, -1.0)
            put(0, 2, 0.0)

            put(1, 0, - 1.0)
            put(1, 1, 4.0)
            put(1, 2, -1.0)

            put(2, 0, 0.0)
            put(2, 1, -1.0)
            put(2, 2, 0.0)
        }
    }

    filter2D(source, destination, -1, kernel)
    imwrite("laplacian.jpg", destination)
}

## ColorSpaceConversion.kt


fun main(args: Array<String>) {
    Origami.init()
    val mat = Imgcodecs.imread("data/dip/digital_image_processing.jpg")
    val mat1 = Mat(mat.width(), mat.height(), CvType.CV_8UC3)
    Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2HSV)
    Imgcodecs.imwrite("hsv.jpg", mat1)
}

## Robinson.kt


fun main(args: Array<String>) {
    init()
    val kernelSize = 3
    val source = imread("data/dip/grayscale.jpg", IMREAD_GRAYSCALE)
    val destination = Mat(source.rows(), source.cols(), source.type())
    val kernel: Mat = object : Mat(kernelSize, kernelSize, CV_32F) {
        init {
            put(0, 0, -1.0)
            put(0, 1, 0.0)
            put(0, 2, 1.0)

            put(1, 0, - 2.0)
            put(1, 1, 0.0)
            put(1, 2, 2.0)

            put(2, 0, -1.0)
            put(2, 1, 0.0)
            put(2, 2, 1.0)
        }
    }

    filter2D(source, destination, -1, kernel)
    imwrite("robinson.jpg", destination)
}

## ApplyingWatermark.kt



fun main(args: Array<String>) {
    Origami.init()
    val source = imread("data/dip/digital_image_processing.jpg", IMREAD_COLOR)
    putText(
        source, "dip.hellonico.info", Point((source.rows() / 2).toDouble(), (source.cols() / 2).toDouble()),
        FONT_ITALIC, 1.0, Scalar(255.0)
    )
    imwrite("watermarked.jpg", source)
}

## BilateralFilter.kt


fun main(args: Array<String>) {
    Origami.init()
    val src = imread("data/marcel2019.jpg")
    val dst = Mat()
    bilateralFilter(src, dst, 15, 80.0, 80.0, Core.BORDER_DEFAULT)
    imwrite("bilateral.jpg", dst)
}

## BoxFilter.kt


fun main(args: Array<String>) {
    Origami.init()
    val src = imread("data/marcel2019.jpg")
    val dst = Mat()
    val size = Size(45.0, 45.0)
    val point = Point(-1.0, -1.0)
    boxFilter(src, dst, 50, size, point, true, Core.BORDER_DEFAULT)
    imwrite("boxFilter.jpg", dst)
}

## SQRBoxFilterTest.kt


fun main(args: Array<String>) {
    Origami.init()
    val src = imread("data/marcel2019.jpg")
    val dst = Mat()
    sqrBoxFilter(src, dst, -1, Size(1.0, 1.0))
    imwrite("sqrBoxFilter.jpg", dst)
}

## Filter2D.kt



fun main(args: Array<String>) {
    Origami.init()
    val src = imread("data/marcel2019.jpg")
    val dst = Mat()
    val kernel = Mat.zeros(5, 5, CvType.CV_32F)
    for (i in 0 until kernel.rows()) {
        for (j in 0 until kernel.cols()) {
            val m = kernel[i, j]
            for (k in 1 until m.size) {
                m[k] = m[k] / (2 * 2)
            }
            kernel.put(i, j, *m)
        }
    }
    println(kernel.dump())
    filter2D(src, dst, -1, kernel)
    imwrite("filter2d.jpg", dst)
}

## BlurTest.kt


fun main(args: Array<String>) {
    Origami.init()
    val (src,dst) = listOf(imread("data/marcel.jpg"), Mat())
    blur(src, dst, Size(100.0, 100.0), Point(20.0, 30.0), BORDER_REFLECT)
    imwrite("blur.jpg", dst)
}

## GaussianTest.kt



fun main(args: Array<String>) {
    Origami.init()
    val src = imread("data/marcel2019.jpg")
    val dst = Mat()
    GaussianBlur(src, dst, Size(45.0, 45.0), 0.0)
    imwrite("blur.jpg", dst)
}

## MedianTest.kt


fun main(args: Array<String>) {
    Origami.init()
    val src = imread("data/marcel2019.jpg")
    val dst = Mat()
    medianBlur(src, dst, 15)
    imwrite("blur.jpg", dst)
}

## OptimizingGrabcut.kt


fun main(args: Array<String>) {
    Origami.init()
    val mat = imread("data/marcel2019.jpg")
    val result = extractFace(mat, 300, 1200, 300, 900)
    imwrite("grabcut.jpg", result)
}

fun extractFace(image: Mat, xOne: Int, xTwo: Int, yOne: Int, yTwo: Int): Mat {
    val rectangle = Rect(xOne, yOne, xTwo, yTwo)
    val result = Mat()
    val bgdModel = Mat()
    val fgdModel = Mat()
    val source = Mat(1, 1, CvType.CV_8U, Scalar(3.0))
    val iteration:Int = 1
    grabCut(image, result, rectangle, bgdModel, fgdModel, iteration, GC_INIT_WITH_RECT)
    compare(result, source, result, CMP_EQ)
    val foreground = Mat(image.size(), CvType.CV_8UC3, Scalar(255.0, 255.0, 255.0))
    image.copyTo(foreground, result)
    return foreground
}

## BodyTransfer.kt


const val DEFAULT_CLASSIFIER =
const val IMAGE_PATH = "image.jpg"
const val CLASSIFIER_PATH = "haarcascade.xml"
val COLOR = Scalar(0.0, 100.0, 0.0)

fun main(args: Array<String>) {
    Origami.init()
    val imageUrl = if (args.size >= 1 && args[0] != null) args[0] else DEFAULT_IMAGE
    val classifierUrl = if (args.size >= 2 && args[1] != null) args[1] else DEFAULT_CLASSIFIER
    Downloader.transfer(imageUrl, IMAGE_PATH)
    Downloader.transfer(classifierUrl, CLASSIFIER_PATH)
    val classifier = CascadeClassifier()
    classifier.load(CLASSIFIER_PATH)
    val mat = imread(IMAGE_PATH)
    val bodies = MatOfRect()
    classifier.detectMultiScale(mat, bodies)

    for (body in bodies.toList()) {
        Imgproc.rectangle(
            mat,
            Point(body.x.toDouble(), body.y.toDouble()),
            Point((body.x + body.width).toDouble(), (body.y + body.height).toDouble()),
            COLOR,
            3
        )
    }

    imwrite("output.jpg", mat)
}

## hello.kt


object HelloCV {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        Origami.init()
        val hello = eye(3, 3, CV_8UC1)
        println(hello.dump())
    }

}

## InPainting.kt


/**
 */

fun main(args: Array<String>) {
    Origami.init()
    val img = imread("data/geeksforgeeks/cat_damaged.png")
    val mask = imread("data/geeksforgeeks/cat_mask.png", 0)
    val dst = Mat()
    inpaint(img, mask, dst, 3.0, INPAINT_NS)
    imwrite("cat_inpainted.png", dst)
}

## Threshold.kt


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

        sliderPanel.add(JLabel(TRACKBAR_TYPE))
        val sliderThreshType = JSlider(0, MAX_TYPE, thresholdType)
        sliderThreshType.majorTickSpacing = 1
        sliderThreshType.minorTickSpacing = 1
        sliderThreshType.paintTicks = true
        sliderThreshType.paintLabels = true
        sliderPanel.add(sliderThreshType)
        sliderPanel.add(JLabel(TRACKBAR_VALUE))
        val sliderThreshValue = JSlider(0, MAX_VALUE, 0)
        sliderThreshValue.majorTickSpacing = 50
        sliderThreshValue.minorTickSpacing = 10
        sliderThreshValue.paintTicks = true
        sliderThreshValue.paintLabels = true
        sliderPanel.add(sliderThreshValue)

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
        pane.add(sliderPanel, BorderLayout.PAGE_START)
        imgLabel = JLabel(ImageIcon(img))
        pane.add(imgLabel, BorderLayout.CENTER)
    }

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

            SwingUtilities.invokeLater { Threshold(args) }
        }

        init {
            Origami.init()
        }
    }

    init {
        var imagePath = "data/stuff.jpg"
        if (args.size > 0) {
            imagePath = args[0]
        }
        src = Imgcodecs.imread(imagePath)
        if (src.empty()) {
            println("Empty image: $imagePath")
            System.exit(0)
        }
        Imgproc.cvtColor(src, srcGray, Imgproc.COLOR_BGR2GRAY)

        frame = JFrame(WINDOW_NAME)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        val img = HighGui.toBufferedImage(srcGray)
        addComponentsToPane(frame.contentPane, img)
        frame.pack()
        frame.isVisible = true
    }
}

## ThresholdInRange.kt


/**
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
        frame = JFrame(WINDOW_NAME)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(windowEvent: WindowEvent) {
                captureTask.cancel(true)
            }
        })
        val img = HighGui.toBufferedImage(matFrame)
        addComponentsToPane(frame.contentPane, img)
        frame.pack()
        frame.isVisible = true

        captureTask.execute()
    }
}

## SurfMatchingDemo.kt


fun SURFMatching(args: Array<String>) {
    val filename1 = if (args.size > 1) args[0] else "data/opencvtutorial/box.png"
    val filename2 = if (args.size > 1) args[1] else "data/opencvtutorial/box_in_scene.png"
    val img1 = imread(filename1, IMREAD_GRAYSCALE)
    val img2 = imread(filename2, IMREAD_GRAYSCALE)

    val hessianThreshold = 400.0
    val nOctaves = 4
    val nOctaveLayers = 3
    val extended = false
    val upright = false

    val detector = SURF.create(hessianThreshold, nOctaves, nOctaveLayers, extended, upright)
    val keypoints1 = MatOfKeyPoint()
    val keypoints2 = MatOfKeyPoint()
    val descriptors1 = Mat()
    val descriptors2 = Mat()
    detector.detectAndCompute(img1, Mat(), keypoints1, descriptors1)
    detector.detectAndCompute(img2, Mat(), keypoints2, descriptors2)

    val matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE)
    val matches = MatOfDMatch()
    matcher.match(descriptors1, descriptors2, matches)

    val imgMatches = Mat()
    Features2d.drawMatches(img1, keypoints1, img2, keypoints2, matches, imgMatches)
    imshow("Matches", imgMatches)
    waitKey(0)
}

fun main(args: Array<String>) {
    Origami.init()
    SURFMatching(args)
}

## MorphologyDemo2.kt


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

        frame = JFrame("Morphology Transformations demo")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        val img = HighGui.toBufferedImage(matImgSrc)
        addComponentsToPane(frame.contentPane, img)
        frame.pack()
        frame.isVisible = true
    }
}

fun main(args: Array<String>) {
    Origami.init()
    SwingUtilities.invokeLater { MorphologyDemo2(args) }
}


## FindContoursDemo.kt


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
        val cannyOutput = Mat()
        Imgproc.Canny(srcGray, cannyOutput, threshold.toDouble(), (threshold * 2).toDouble())

        val contours: List<MatOfPoint> = ArrayList()
        val hierarchy = Mat()
        Imgproc.findContours(cannyOutput, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE)

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
        val filename = if (args.size > 0) args[0] else "data/HappyFish.jpg"
        val src = Imgcodecs.imread(filename)
        if (src.empty()) {
            System.err.println("Cannot read image: $filename")
            System.exit(0)
        }

        Imgproc.cvtColor(src, srcGray, Imgproc.COLOR_BGR2GRAY)
        Imgproc.blur(srcGray, srcGray, Size(3.0, 3.0))

        frame = JFrame("Finding contours in your image demo")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        val img = HighGui.toBufferedImage(src)
        addComponentsToPane(frame.contentPane, img)
        frame.pack()
        frame.isVisible = true
        update()
    }
}

object FindContoursDemo {
    @JvmStatic
    fun main(args: Array<String>) {

        SwingUtilities.invokeLater { FindContours(args) }
    }

    init {
        try {
        } catch (e: Exception) {
        }
    }
}

## simple.kt


fun main(args: Array<String>) {
    init()
    Camera().run()
}

## simpleWithFilter.kt


fun main(args: Array<String>) {
    init()
    Camera().filter(Function { im ->
        val (temp,target) = listOf(Mat(), Mat())
        val dx = 16.0
        resize(im, temp, Size(dx, dx), 1.0, 1.0, INTER_LINEAR)
        resize(temp, target, im.size(), 1.0, 1.0, INTER_NEAREST)
        target
    }).run()
}

