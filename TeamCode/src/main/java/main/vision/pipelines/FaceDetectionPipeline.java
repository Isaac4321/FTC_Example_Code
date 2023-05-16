package main.vision.pipelines;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.openftc.easyopencv.OpenCvPipeline;

/**
 * A basic vision pipeline for detecting faces using the HaarCascadeClassifier.
 * An example use is to program the robot to drive towards the faces. (at a low speed hopefully)
 */
public class FaceDetectionPipeline extends OpenCvPipeline {
    private boolean faceDetected = false;
    private final Telemetry telemetry;



    /** Constructor used for EOCV simulator. Allows for live telemetry viewing. */
    public FaceDetectionPipeline(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.medianBlur(input, input, 1);
        CascadeClassifier frontalFaceClassifier = new CascadeClassifier("C:\\Users\\isaac\\StudioProjects\\FTC_Example_Code\\TeamCode\\src\\main\\java\\main\\vision\\opencvxml\\haarcascade-frontalface-default.xml");
        CascadeClassifier profileFaceClassifier = new CascadeClassifier();

        MatOfRect frontFaceDetections = new MatOfRect();
        frontalFaceClassifier.detectMultiScale(input, frontFaceDetections, 1.3, 5);

        faceDetected = !frontFaceDetections.empty();
        for (Rect rect : frontFaceDetections.toArray()) {
            Imgproc.rectangle(
                    input,
                    new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 0, 0),
                    2
            );
        }
        telemetry.addData("Face detected: ", faceDetected);
        telemetry.update();
        return input;
    }

    /** Returns true if a face is detected. */
    public boolean isFaceDetected() {
        return faceDetected;
    }
}
