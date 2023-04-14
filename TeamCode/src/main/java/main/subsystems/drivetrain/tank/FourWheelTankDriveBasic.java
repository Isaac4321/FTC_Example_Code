package main.subsystems.drivetrain.tank;

import android.os.Build;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;


import main.subsystems.drivetrain.SubsystemBase;

/*
    Motor Layout
     FLM | FRM
         |
         |
         |
     RLM | RRM
 */
public class FourWheelTankDriveBasic extends SubsystemBase {
    private final DcMotorEx frontLeftMotor;
    private final DcMotorEx frontRightMotor;
    private final DcMotorEx rearLeftMotor;
    private final DcMotorEx rearRightMotor;

    public FourWheelTankDriveBasic(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "frontRightMotor");
        rearLeftMotor = hardwareMap.get(DcMotorEx.class, "rearLeftMotor");
        rearRightMotor = hardwareMap.get(DcMotorEx.class, "rearRightMotor");

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void drive(double forward, double turn, boolean scaled) {
        if (scaled) {
            frontLeftMotor.setPower(Range.clip(Math.pow(forward + turn, 2), -1, 1));
            frontRightMotor.setPower(Range.clip(Math.pow(forward + turn, 2), -1, 1));
            rearLeftMotor.setPower(Range.clip(Math.pow(forward - turn, 2), -1, 1));
            rearRightMotor.setPower(Range.clip(Math.pow(forward - turn, 2), -1, 1));
        }
        else {
            frontLeftMotor.setPower(Range.clip(forward + turn, -1, 1));
            frontRightMotor.setPower(Range.clip(forward + turn, -1, 1));
            rearLeftMotor.setPower(Range.clip(forward + turn, -1, 1));
            rearRightMotor.setPower(Range.clip(forward + turn, -1, 1));
        }
    }
}
