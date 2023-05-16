package main.subsystems.misc;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.R;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import main.subsystems.SubsystemBase;
import main.vision.pipelines.FaceDetectionPipeline;

public class WebcamSubsystem extends SubsystemBase {
    private OpenCvWebcam webcam;

    private final FaceDetectionPipeline pipeline;

    private boolean isStreaming = false;

    private final int WIDTH = 640;
    private final int HEIGHT = 360;

    private final OpenCvCameraRotation ROTATION = OpenCvCameraRotation.UPRIGHT;

    public WebcamSubsystem(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);
        int cameraViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraViewId);

        pipeline = new FaceDetectionPipeline(telemetry);
        webcam.setPipeline(pipeline);
    }

    public void startStreaming() {
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(WIDTH, HEIGHT, ROTATION);
                isStreaming = true;
            }

            @Override
            public void onError(int errorCode) {
                isStreaming = false;
            }
        });
    }

    public FaceDetectionPipeline getPipeline() {
        return pipeline;
    }

    public boolean isStreaming() {
        return isStreaming;
    }
}
