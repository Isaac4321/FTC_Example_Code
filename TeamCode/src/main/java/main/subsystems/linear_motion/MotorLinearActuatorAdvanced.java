package main.subsystems.linear_motion;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import main.subsystems.SubsystemBase;
import main.subsystems.drivetrain.holonomic.MecanumHolonomicDriveAdvanced;

/**
 * An example subsystem representing a linear actuator driven by one motor.
 * This is a advanced version with autonomous capability's.
 */
public class MotorLinearActuatorAdvanced extends SubsystemBase {
    private static final class Constants {
        public static final double EXTEND_SPEED = 0.60;
        public static final double SHRINK_SPEED = -0.30;

        public static final double PULSES_PER_MOTOR_REV = -1;
        public static final double DRIVE_GEAR_REDUCTION = -1;
        public static final double WHEEL_DIAMETER_CENTIMETER = -1;
        public static final double PULSES_PER_CENTIMETER =
                (PULSES_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_CENTIMETER * Math.PI);

        public static final double MAX_EXTENSION = 10;
        public static final double MIN_EXTENSION = 0;
    }

    private final DcMotorEx actuatorMotor;

    /** Initializes the servo, make sure to have the servo mapped in your robot configuration. */
    public MotorLinearActuatorAdvanced(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        actuatorMotor = hardwareMap.get(DcMotorEx.class, "slideMotor");

        actuatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        actuatorMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        actuatorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        actuatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /** Extends the actuator at a constant speed. */
    public void extend() {
        actuatorMotor.setPower(Constants.EXTEND_SPEED);
    }

    /** Shrinks the actuator at a constant speed. */
    public void shrink() {
        actuatorMotor.setPower(Constants.SHRINK_SPEED);
    }

    /** Stops the actuator from moving. */
    public void stop() {
        actuatorMotor.setPower(0);
    }

    /** Extends/shrinks the linear actuator a specific distance. */
    public void extendTo(double distance, DistanceUnit distanceUnit) {
        actuatorMotor.setTargetPosition(actuatorMotor.getCurrentPosition() + (int) distanceUnit.toPulses(distance));
        actuatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        extend();
        while (!atMaxExtension() && !atMinExtension() && actuatorMotor.isBusy()) {

        }
        stop();
        actuatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /** Returns if the linkage is at maximum extension. */
    public boolean atMaxExtension() {
        return actuatorMotor.getCurrentPosition() / Constants.PULSES_PER_CENTIMETER > Constants.MAX_EXTENSION - 1.5;
    }

    /** Returns if the linkage is at minimum extension. */
    public boolean atMinExtension() {
        return actuatorMotor.getCurrentPosition() / Constants.PULSES_PER_CENTIMETER < Constants.MIN_EXTENSION + 1.5;
    }

    /** A enum for different distance units eg. inches, centimeters. */
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
