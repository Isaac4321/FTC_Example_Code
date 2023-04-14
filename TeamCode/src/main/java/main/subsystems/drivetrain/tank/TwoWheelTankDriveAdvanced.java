package main.subsystems.drivetrain.tank;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import main.subsystems.SubsystemBase;

/*
    Motor Layout
 {wheel} | {wheel}
         |
         |
         |
      LM | RM
 */
public class TwoWheelTankDriveAdvanced extends SubsystemBase {
    private static class Constants {
        private static final boolean SCALED = false;

        private static final double FORWARD_DRIVE_SPEED = 0.5;

        private static final double P_DRIVE_GAIN = 0.01;
        private static final double P_TURN_GAIN = 0.01;
        private static final double HEADING_THRESHOLD = 0.01;

        public static final double PULSES_PER_MOTOR_REV = -1;
        public static final double DRIVE_GEAR_REDUCTION = -1;
        public static final double WHEEL_DIAMETER_CENTIMETER = -1;
        public static final double PULSES_PER_CENTIMETER =
                (PULSES_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_CENTIMETER * Math.PI);
    }

    private final DcMotorEx leftMotor;
    private final DcMotorEx rightMotor;
    private final BNO055IMU imu;

    private double robotHeading = 0;
    private double headingOffset = 0;
    private double headingError = 0;

    public TwoWheelTankDriveAdvanced(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;

        imu.initialize(parameters);
    }

    public void drive(double forward, double turn) {
        if (Constants.SCALED) {
            leftMotor.setPower(Range.clip(Math.pow(forward + turn, 2), -1, 1));
            rightMotor.setPower(Range.clip(Math.pow(forward - turn, 2), -1, 1));
        }
        else {
            leftMotor.setPower(Range.clip(forward + turn, -1, 1));
            rightMotor.setPower(Range.clip(forward - turn, -1, 1));
        }
    }

    private void autoDriveRobot(double forward, double turn) {
        leftMotor.setPower(Range.clip(forward + turn, -1, 1));
        rightMotor.setPower(Range.clip(forward - turn, -1, 1));
    }

    public void driveStraight(double distance, DistanceUnit distanceUnit) {
        int target = (int) distanceUnit.toPulses(distance);

        leftMotor.setTargetPosition(leftMotor.getCurrentPosition() + target);
        rightMotor.setTargetPosition(rightMotor.getCurrentPosition() - target);

        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        autoDriveRobot(Constants.FORWARD_DRIVE_SPEED, 0);
        while (leftMotor.isBusy() && rightMotor.isBusy()) { }
        autoDriveRobot(0, 0);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turnToHeading(double heading) {
        getSteeringCorrection(heading, Constants.P_DRIVE_GAIN);

        while(Math.abs(headingError) > Constants.HEADING_THRESHOLD) {
            double turn = getSteeringCorrection(heading, Constants.P_TURN_GAIN);
            autoDriveRobot(0, turn);
        }
        autoDriveRobot(0, 0);
    }

    public double getSteeringCorrection(double heading, double proportionalGain) {
        robotHeading = getRawHeading() - headingOffset;
        headingError = heading - robotHeading;

        while (headingError < 180) headingError -= 360;
        while (headingError <= -180) headingError += 360;

        return Range.clip(headingError * proportionalGain, -1, 1);
    }

    public double getRawHeading() {
        return imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
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
