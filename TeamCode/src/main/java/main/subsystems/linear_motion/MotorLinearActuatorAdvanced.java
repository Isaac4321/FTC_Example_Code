package main.subsystems.linear_motion;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import main.subsystems.SubsystemBase;
import main.subsystems.drivetrain.holonomic.MecanumHolonomicDriveAdvanced;

public class MotorLinearActuatorAdvanced extends SubsystemBase {
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

    private final DcMotorEx actuatorMotor;

    public MotorLinearActuatorAdvanced(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        actuatorMotor = hardwareMap.get(DcMotorEx.class, "slideMotor");

        actuatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        actuatorMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        actuatorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        actuatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void power(double power) {
        actuatorMotor.setPower(Range.clip(power, -1, 1));
    }

    public void extendTo(double distance, DistanceUnit distanceUnit) {
        actuatorMotor.setTargetPosition(actuatorMotor.getCurrentPosition() + (int) distanceUnit.toPulses(distance));
        actuatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (!atMaxExtension() && !atMinExtension() && actuatorMotor.isBusy()) {
            power(Constants.EXTENSION_SPEED);
        }
        power(0);
        actuatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public boolean atMaxExtension() {
        return actuatorMotor.getCurrentPosition() / Constants.PULSES_PER_CENTIMETER > Constants.MAX_EXTENSION - 1.5;
    }

    public boolean atMinExtension() {
        return actuatorMotor.getCurrentPosition() / Constants.PULSES_PER_CENTIMETER < Constants.MIN_EXTENSION + 1.5;
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