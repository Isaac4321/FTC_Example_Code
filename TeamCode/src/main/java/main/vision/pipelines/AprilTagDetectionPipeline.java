package main.vision.pipelines;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvPipeline;

//TODO: implement AprilTagDetectionPipeline
public class AprilTagDetectionPipeline extends OpenCvPipeline {
    public final Scalar lowerBound = new Scalar(20, 20, 20);
    public final Scalar upperBound = new Scalar(100, 100, 100);

    @Override
    public Mat processFrame(Mat input) {
        Core.inRange(input, lowerBound, upperBound, input);
        return input;
    }
}
