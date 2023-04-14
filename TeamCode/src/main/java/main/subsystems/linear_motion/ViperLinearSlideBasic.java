package main.subsystems.linear_motion;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import main.subsystems.SubsystemBase;

public class ViperLinearSlideBasic extends SubsystemBase {
    private final DcMotorEx slideMotor;

    public ViperLinearSlideBasic(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        slideMotor = hardwareMap.get(DcMotorEx.class, "slideMotor");

        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void power(double power) {
        slideMotor.setPower(Range.clip(power, -1, 1));
    }
}
