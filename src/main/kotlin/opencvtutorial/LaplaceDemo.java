package opencvtutorial;
/**
 * @file opencvtutorial.LaplaceDemo.java
 * @brief Sample code showing how to detect edges using the Laplace operator
 */

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import origami.Origami;

class LaplaceDemoRun {

    public void run(String[] args) {
        // ! [variables]
        // Declare the variables we are going to use
        Mat src, src_gray = new Mat(), dst = new Mat();
        int kernel_size = 3;
        int scale = 1;
        int delta = 0;
        int ddepth = CvType.CV_16S;
        String window_name = "Laplace Demo";
        // ! [variables]

        // ! [load]
        String imageName = ((args.length > 0) ? args[0] : "data/lena.jpg");

        src = Imgcodecs.imread(imageName, Imgcodecs.IMREAD_COLOR); // Load an image

        // Check if image is loaded fine
        if (src.empty()) {
            System.out.println("Error opening image");
            System.out.println("Program Arguments: [image_name -- default ../data/lena.jpg] \n");
            System.exit(-1);
        }
        // ! [load]

        // ! [reduce_noise]
        // Reduce noise by blurring with a Gaussian filter ( kernel size = 3 )
        Imgproc.GaussianBlur(src, src, new Size(3, 3), 0, 0, Core.BORDER_DEFAULT);
        // ! [reduce_noise]

        // ! [convert_to_gray]
        // Convert the image to grayscale
        Imgproc.cvtColor(src, src_gray, Imgproc.COLOR_RGB2GRAY);
        // ! [convert_to_gray]

        /// Apply Laplace function
        Mat abs_dst = new Mat();
        // ! [laplacian]
        Imgproc.Laplacian(src_gray, dst, ddepth, kernel_size, scale, delta, Core.BORDER_DEFAULT);
        // ! [laplacian]

        // ! [convert]
        // converting back to CV_8U
        Core.convertScaleAbs(dst, abs_dst);
        // ! [convert]

        // ! [display]
        HighGui.imshow(window_name, abs_dst);
        HighGui.waitKey(0);
        // ! [display]

        System.exit(0);
    }
}

public class LaplaceDemo {
    static {
        try {
            // Load the native OpenCV library
            Origami.init();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {

        new LaplaceDemoRun().run(args);
    }
}
