package main.subsystems.drivetrain.tank;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import main.subsystems.drivetrain.SubsystemBase;

/*
    Motor Layout
 {wheel} | {wheel}
         |
         |
         |
      LM | RM
 */
public class TwoWheelTankDriveBasic extends SubsystemBase {
    private final DcMotorEx leftMotor;
    private final DcMotorEx rightMotor;

    public TwoWheelTankDriveBasic(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void drive(double forward, double turn, boolean scaled) {
        if (scaled) {
            leftMotor.setPower(Range.clip(Math.pow(forward + turn, 2), -1, 1));
            rightMotor.setPower(Range.clip(Math.pow(forward - turn, 2), -1, 1));
        }
        else {
            leftMotor.setPower(Range.clip(forward + turn, -1, 1));
            rightMotor.setPower(Range.clip(forward - turn, -1, 1));
        }
    }
}
