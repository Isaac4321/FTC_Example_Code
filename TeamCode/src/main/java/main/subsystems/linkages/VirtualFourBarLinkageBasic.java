package main.subsystems.linkages;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import main.subsystems.SubsystemBase;

public class VirtualFourBarLinkageBasic extends SubsystemBase {
    private static class Constants {
        public static final double LIFT_SPEED = 0.60;
        public static final double DROP_SPEED = -0.30;
    }

    private final DcMotor linkageMotor;

    public VirtualFourBarLinkageBasic(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);
        linkageMotor = hardwareMap.get(DcMotor.class, "linkageMotor");

        linkageMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linkageMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void lift() {
        linkageMotor.setPower(Constants.LIFT_SPEED);
    }

    public void drop() {
        linkageMotor.setPower(Constants.DROP_SPEED);
    }

    public void stop() {
        linkageMotor.setPower(0);
    }
}
