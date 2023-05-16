package main.subsystems.linear_motion;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import main.subsystems.SubsystemBase;

/**
 * An example subsystem representing a linear actuator driven by one motor.
 * This is a basic version with no autonomous capability's.
 */
public class MotorLinearActuatorBasic extends SubsystemBase {
    private static class Constants {
        public static final double EXTEND_SPEED = 0.60;
        public static final double SHRINK_SPEED = -0.30;
    }

    private final DcMotor actuatorMotor;

    /** Initializes the servo, make sure to have the servo mapped in your robot configuration. */
    public MotorLinearActuatorBasic(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);
        actuatorMotor = hardwareMap.get(DcMotor.class, "actuatorMotor");

        actuatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        actuatorMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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
}
