package main.vision;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

//TODO: Write more specific docs on each of these functions and when they will be used.
public class BasicImageProcessing extends OpenCvPipeline {
    private final Mat output = new Mat();

    @Override
    public Mat processFrame(Mat input) {
        // Image Smoothing -> change the size parameters to see difference in image smoothing.
        Imgproc.blur(input, output, new Size(2, 2), new Point(-1, -1));
        Imgproc.GaussianBlur(input, output, new Size(2, 2), 0, 0);
        Imgproc.medianBlur(input, output, 2);
        Imgproc.bilateralFilter(input, output, 2, 2, 2);

        // Erosion and Dilation


        return null;
    }
}
