package main.opmodes.teleop;

import android.os.Build;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.opencv.calib3d.Calib3d;
import org.opencv.imgcodecs.Imgcodecs;

import main.robot.Robot;

@TeleOp(name="Robot Dog")
public class RobotFollowOpMode extends LinearOpMode {
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            robot = new Robot(this);
            robot.initWebcam();
        }

        waitForStart();
        if (opModeIsActive()) {
            robot.getWebcamSubsystem().startStreaming();
        }
        while (opModeIsActive() && !isStopRequested()) {
            if (!robot.getWebcamSubsystem().isStreaming()) {
                continue;
            }
            while (!robot.getWebcamSubsystem().getPipeline().isFaceDetected()) {
                robot.getDriveSubsystem().drive(0, 0, .10);
            }
            robot.getDriveSubsystem().drive(.10, 0, 0);
        }
    }
}
