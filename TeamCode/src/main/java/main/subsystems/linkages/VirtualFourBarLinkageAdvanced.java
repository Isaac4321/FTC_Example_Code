package main.subsystems.linkages;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;

import main.subsystems.SubsystemBase;

public class VirtualFourBarLinkageAdvanced extends SubsystemBase {
    private static class Constants {
        public static final double LIFT_SPEED = 0.60;
        public static final double DROP_SPEED = -0.30;

        public static final double PULSES_PER_MOTOR_REV = -1;
        public static final double PULSES_PER_DEGREE = PULSES_PER_MOTOR_REV / 360;

        private static final Position[] positions = new Position[]{Position.HOME, Position.POSITION1, Position.POSITION2, Position.POSITION3};
    }

    private final DcMotor linkageMotor;

    private Position linkagePosition;
    private int cursor;

    public VirtualFourBarLinkageAdvanced(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);
        linkageMotor = hardwareMap.get(DcMotor.class, "linkageMotor");

        linkageMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linkageMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        linkageMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linkageMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        linkagePosition = Constants.positions[0];
        cursor = 0;
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

    public void nextPos() {
        if (linkagePosition == Position.POSITION3) {
            return;
        }
        cursor++;
        linkagePosition = Constants.positions[cursor];

        linkageMotor.setTargetPosition((int) linkagePosition.getTargetPosition());
        linkageMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lift();
        while (linkageMotor.isBusy()) {

        }
        stop();
        linkageMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void prevPos() {
        if (linkagePosition == Position.HOME) {
            return;
        }
        cursor--;
        linkagePosition = Constants.positions[cursor];

        linkageMotor.setTargetPosition((int) linkagePosition.getTargetPosition());
        linkageMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        drop();
        while (linkageMotor.isBusy()) {

        }
        stop();
        linkageMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public enum Position {
        HOME(0),
        POSITION1(10),
        POSITION2(20),
        POSITION3(30);

        private int deg;

        Position(int deg) {
            this.deg = deg;
        }

        public double getTargetPosition() {
            return this.deg * Constants.PULSES_PER_DEGREE;
        }

    }
}
