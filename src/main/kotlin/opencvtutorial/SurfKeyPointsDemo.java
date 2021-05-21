package opencvtutorial;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.Features2d;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.xfeatures2d.SURF;
import origami.Origami;

import java.io.IOException;

class SurfKeyPointsDetection {
    public void run(String[] args) {
        String filename = args.length > 0 ? args[0] : "data/box.png";
        Mat src = Imgcodecs.imread(filename, Imgcodecs.IMREAD_GRAYSCALE);
        if (src.empty()) {
            System.err.println("Cannot read image: " + filename);
            System.exit(0);
        }

        // -- Step 1: Detect the keypoints using SURF Detector
        double hessianThreshold = 400;
        int nOctaves = 4, nOctaveLayers = 3;
        boolean extended = false, upright = false;
        SURF detector = SURF.create(hessianThreshold, nOctaves, nOctaveLayers, extended, upright);
        MatOfKeyPoint keypoints = new MatOfKeyPoint();
        detector.detect(src, keypoints);

        // -- Draw keypoints
        Features2d.drawKeypoints(src, keypoints, src);

        // -- Show detected (drawn) keypoints
        Imgcodecs.imwrite("surf_result.png", src);
        HighGui.imshow("SURF Keypoints", src);
        HighGui.waitKey();

        System.exit(0);
    }
}

public class SurfKeyPointsDemo {
    public static void main(String[] args) throws IOException {
        Origami.init();
        new SurfKeyPointsDetection().run(args);
    }
}
