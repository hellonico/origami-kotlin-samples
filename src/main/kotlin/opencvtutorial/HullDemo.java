package opencvtutorial;

import org.opencv.core.Point;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import origami.Origami;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Hull {
    private Mat srcGray = new Mat();
    private JFrame frame;
    private JLabel imgSrcLabel;
    private JLabel imgContoursLabel;
    private static final int MAX_THRESHOLD = 255;
    private int threshold = 100;
    private Random rng = new Random(12345);

    public Hull(String[] args) {
        /// Load source image
        String filename = args.length > 0 ? args[0] : "data/stuff.jpg";
        Mat src = Imgcodecs.imread(filename);
        if (src.empty()) {
            System.err.println("Cannot read image: " + filename);
            System.exit(0);
        }

        /// Convert image to gray and blur it
        Imgproc.cvtColor(src, srcGray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.blur(srcGray, srcGray, new Size(3, 3));

        // Create and set up the window.
        frame = new JFrame("Convex opencvtutorial.Hull demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set up the content pane.
        Image img = HighGui.toBufferedImage(src);
        addComponentsToPane(frame.getContentPane(), img);
        // Use the content pane's default BorderLayout. No need for
        // setLayout(new BorderLayout());
        // Display the window.
        frame.pack();
        frame.setVisible(true);
        update();
    }

    private void addComponentsToPane(Container pane, Image img) {
        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.PAGE_AXIS));

        sliderPanel.add(new JLabel("Canny threshold: "));
        JSlider slider = new JSlider(0, MAX_THRESHOLD, threshold);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                threshold = source.getValue();
                update();
            }
        });
        sliderPanel.add(slider);
        pane.add(sliderPanel, BorderLayout.PAGE_START);

        JPanel imgPanel = new JPanel();
        imgSrcLabel = new JLabel(new ImageIcon(img));
        imgPanel.add(imgSrcLabel);

        Mat blackImg = Mat.zeros(srcGray.size(), CvType.CV_8U);
        imgContoursLabel = new JLabel(new ImageIcon(HighGui.toBufferedImage(blackImg)));
        imgPanel.add(imgContoursLabel);

        pane.add(imgPanel, BorderLayout.CENTER);
    }

    private void update() {
        /// Detect edges using Canny
        Mat cannyOutput = new Mat();
        Imgproc.Canny(srcGray, cannyOutput, threshold, threshold * 2);

        /// Find contours
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(cannyOutput, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        /// Find the convex hull object for each contour
        List<MatOfPoint> hullList = new ArrayList<>();
        for (MatOfPoint contour : contours) {
            MatOfInt hull = new MatOfInt();
            Imgproc.convexHull(contour, hull);

            Point[] contourArray = contour.toArray();
            Point[] hullPoints = new Point[hull.rows()];
            List<Integer> hullContourIdxList = hull.toList();
            for (int i = 0; i < hullContourIdxList.size(); i++) {
                hullPoints[i] = contourArray[hullContourIdxList.get(i)];
            }
            hullList.add(new MatOfPoint(hullPoints));
        }

        /// Draw contours + hull results
        Mat drawing = Mat.zeros(cannyOutput.size(), CvType.CV_8UC3);
        for (int i = 0; i < contours.size(); i++) {
            Scalar color = new Scalar(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
            Imgproc.drawContours(drawing, contours, i, color);
            Imgproc.drawContours(drawing, hullList, i, color);
        }

        imgContoursLabel.setIcon(new ImageIcon(HighGui.toBufferedImage(drawing)));
        frame.repaint();
    }
}

public class HullDemo {
    static {
        try {
            // Load the native OpenCV library
            Origami.init();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {

        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Hull(args);
            }
        });
    }
}
