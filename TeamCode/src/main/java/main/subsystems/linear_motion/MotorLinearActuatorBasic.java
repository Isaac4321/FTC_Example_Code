package main.subsystems.linear_motion;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import main.subsystems.SubsystemBase;

public class MotorLinearActuatorBasic extends SubsystemBase {
    private final DcMotorEx actuatorMotor;

    public MotorLinearActuatorBasic(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        actuatorMotor = hardwareMap.get(DcMotorEx.class, "actuatorMotor");

        actuatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        actuatorMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void power(double power) {
        actuatorMotor.setPower(Range.clip(power, -1, 1));
    }


}
