package main.subsystems.linear_motion;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import main.subsystems.SubsystemBase;

/**
 * Example subsystem for controlling a cascading Viper linear slide.
 * This basic version has no autonomous implemented, it is merely a class for basic control.
 */
public class ViperLinearSlideBasic extends SubsystemBase {
    private static class Constants {
        public static final double EXTEND_SPEED = 0.60;
        public static final double SHRINK_SPEED = -0.30;
    }

    private final DcMotorEx slideMotor;

    public ViperLinearSlideBasic(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);
        slideMotor = hardwareMap.get(DcMotorEx.class, "slideMotor");

        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void extend() {
        slideMotor.setPower(Constants.EXTEND_SPEED);
    }

    public void shrink() {
        slideMotor.setPower(Constants.SHRINK_SPEED);
    }

    public void stop() {
        slideMotor.setPower(0);
    }
}
