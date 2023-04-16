package main.robot;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcontroller.external.samples.SampleRevBlinkinLedDriver;

import main.subsystems.drivetrain.holonomic.MecanumHolonomicDriveAdvanced;

public class Robot extends RobotBase {
    private MecanumHolonomicDriveAdvanced driveSubsystem;

    private final Gamepad controller1;

    public Robot(OpMode opMode) {
        super(opMode);

        controller1 = opMode.gamepad1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initSubsystems() {
        driveSubsystem = new MecanumHolonomicDriveAdvanced(opMode.telemetry, opMode.hardwareMap);
    }

    @Override
    public void run() {
        driveSubsystem.drive(-controller1.left_stick_y, controller1.left_stick_x, controller1.right_stick_x);
    }
}
