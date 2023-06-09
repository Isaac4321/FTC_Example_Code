package main.robot;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import main.subsystems.drivetrain.holonomic.MecanumHolonomicDriveAdvanced;
import main.subsystems.misc.WebcamSubsystem;

public class Robot extends RobotBase {
    private MecanumHolonomicDriveAdvanced driveSubsystem;
    private WebcamSubsystem webcamSubsystem;

    private final Gamepad controller1;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Robot(OpMode opMode) {
        super(opMode);
        initSubsystems();
        controller1 = opMode.gamepad1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initSubsystems() {
        driveSubsystem = new MecanumHolonomicDriveAdvanced(opMode.telemetry, opMode.hardwareMap);
    }

    public void initWebcam() {
        webcamSubsystem = new WebcamSubsystem(opMode.telemetry, opMode.hardwareMap);

    }

    @Override
    public void run() {
        driveSubsystem.drive(-controller1.left_stick_y, controller1.left_stick_x, controller1.right_stick_x);
    }

    public MecanumHolonomicDriveAdvanced getDriveSubsystem() {
        return driveSubsystem;
    }

    public WebcamSubsystem getWebcamSubsystem() {
        return webcamSubsystem;
    }
}

