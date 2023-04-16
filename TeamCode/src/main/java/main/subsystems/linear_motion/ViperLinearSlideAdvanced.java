package main.subsystems.linear_motion;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import main.subsystems.SubsystemBase;

public class ViperLinearSlideAdvanced extends SubsystemBase {
    private static final class Constants {
        private static final double EXTENSION_SPEED = 0.1;

        public static final double PULSES_PER_MOTOR_REV = -1;
        public static final double DRIVE_GEAR_REDUCTION = -1;
        public static final double WHEEL_DIAMETER_CENTIMETER = -1;
        public static final double PULSES_PER_CENTIMETER =
                (PULSES_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_CENTIMETER * Math.PI);

        public static final double MAX_EXTENSION = 10;
        public static final double MIN_EXTENSION = 0;
    }

    private final DcMotorEx slideMotor;

    public ViperLinearSlideAdvanced(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        slideMotor = hardwareMap.get(DcMotorEx.class, "slideMotor");

        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void power(double power) {
        slideMotor.setPower(Range.clip(power, -1, 1));
    }

    public void extend(double distance, DistanceUnit distanceUnit) {
        slideMotor.setTargetPosition(slideMotor.getCurrentPosition() + (int) distanceUnit.toPulses(distance));
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (!atMaxExtension() && !atMinExtension() && slideMotor.isBusy()) {
            power(Constants.EXTENSION_SPEED);
        }
        power(0);
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public boolean atMaxExtension() {
        return slideMotor.getCurrentPosition() / Constants.PULSES_PER_CENTIMETER > Constants.MAX_EXTENSION - 1.5;
    }

    public boolean atMinExtension() {
        return slideMotor.getCurrentPosition() / Constants.PULSES_PER_CENTIMETER < Constants.MIN_EXTENSION + 1.5;
    }

    public enum DistanceUnit {
        CENTIMETRES(Constants.PULSES_PER_CENTIMETER);

        private final double conversionFactor;

        DistanceUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double toPulses(double value) {
            return value * conversionFactor;
        }
    }
}
