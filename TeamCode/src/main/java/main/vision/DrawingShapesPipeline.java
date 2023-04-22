package main.vision;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class DrawingShapesPipeline extends OpenCvPipeline {
    private final Mat output = new Mat();

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.rectangle(input, new Point(20, 40), new Point(40, 80), new Scalar(0, 255, 255));
        return input;
    }
}
