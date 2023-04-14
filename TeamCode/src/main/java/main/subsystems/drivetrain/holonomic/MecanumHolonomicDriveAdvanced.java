package main.subsystems.drivetrain.holonomic;

import android.os.Build;

import androidx.annotation.RequiresApi;

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
import java.util.Arrays;
import main.subsystems.SubsystemBase;

/*
    Motor Layout
     FLM | FRM
         |
         |
         |
     RLM | RRM
 */
public class MecanumHolonomicDriveAdvanced extends SubsystemBase {
    private static class Constants {
        private static final boolean SCALED = false;
        private static final boolean FIELD_CENTRIC = false;

        private static final double FORWARD_DRIVE_SPEED = 0.5;
        private static final double STRAFE_DRIVE_SPEED = 0.5;

        private static final double P_DRIVE_GAIN = 0.01;
        private static final double P_TURN_GAIN = 0.01;
        private static final double HEADING_THRESHOLD = 0.01;

        public static final double PULSES_PER_MOTOR_REV = -1;
        public static final double DRIVE_GEAR_REDUCTION = -1;
        public static final double WHEEL_DIAMETER_CENTIMETER = -1;
        public static final double PULSES_PER_CENTIMETER =
                (PULSES_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_CENTIMETER * Math.PI);
    }

    private final DcMotorEx frontLeftMotor;
    private final DcMotorEx frontRightMotor;
    private final DcMotorEx rearLeftMotor;
    private final DcMotorEx rearRightMotor;
    private final DcMotorEx[] motors;

    private final BNO055IMU imu;

    private double robotHeading = 0;
    private double headingOffset = 0;
    private double headingError = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public MecanumHolonomicDriveAdvanced(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "frontRightMotor");
        rearLeftMotor = hardwareMap.get(DcMotorEx.class, "rearLeftMotor");
        rearRightMotor = hardwareMap.get(DcMotorEx.class, "rearRightMotor");
        motors = new DcMotorEx[]{frontLeftMotor, frontRightMotor, rearLeftMotor, rearRightMotor};

        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);


        Arrays.stream(motors).forEach(motor -> motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE));
        Arrays.stream(motors).forEach(motor -> motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        Arrays.stream(motors).forEach(motor -> motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER));

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;

        imu.initialize(parameters);
    }

    public void drive(double forward, double strafe, double turn) {
        if (Constants.FIELD_CENTRIC) {
            double gyroRadians = Math.toRadians(-imu.getAngularOrientation().firstAngle);

            forward = strafe * Math.sin(gyroRadians) + forward * Math.cos(gyroRadians);
            strafe = strafe * Math.cos(gyroRadians) - forward * Math.sin(gyroRadians);
        }
        if (Constants.SCALED) {
            frontLeftMotor.setPower(Range.clip(Math.pow(forward + strafe + turn, 2), -1, 1));
            frontRightMotor.setPower(Range.clip(Math.pow(forward - strafe - turn, 2), -1, 1));
            rearLeftMotor.setPower(Range.clip(Math.pow(forward - strafe + turn, 2), -1, 1));
            rearRightMotor.setPower(Range.clip(Math.pow(forward + strafe - turn, 2), -1, 1));
        }
        else {
            frontLeftMotor.setPower(Range.clip(forward + strafe + turn, -1, 1));
            frontRightMotor.setPower(Range.clip(forward - strafe - turn, -1, 1));
            rearLeftMotor.setPower(Range.clip(forward - strafe + turn, -1, 1));
            rearRightMotor.setPower(Range.clip(forward + strafe - turn, -1, 1));
        }
    }

    private void autoDriveRobot(double forward, double strafe, double turn) {
        frontLeftMotor.setPower(Range.clip(forward + strafe + turn, -1, 1));
        frontRightMotor.setPower(Range.clip(forward - strafe - turn, -1, 1));
        rearLeftMotor.setPower(Range.clip(forward - strafe + turn, -1, 1));
        rearRightMotor.setPower(Range.clip(forward + strafe - turn, -1, 1));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void driveStraight(double distance, DistanceUnit distanceUnit) {
        int target = (int) distanceUnit.toPulses(distance);

        Arrays.stream(motors).forEach(motor -> motor.setTargetPosition(motor.getCurrentPosition() + target));

        Arrays.stream(motors).forEach(motor -> motor.setMode(DcMotor.RunMode.RUN_TO_POSITION));

        autoDriveRobot(Constants.FORWARD_DRIVE_SPEED, 0, 0);
        while (frontLeftMotor.isBusy() && frontRightMotor.isBusy() &&
                rearLeftMotor.isBusy() && rearRightMotor.isBusy()) { }
        autoDriveRobot(0, 0, 0);

        Arrays.stream(motors).forEach(motor -> motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void strafeStraight(double distance, DistanceUnit distanceUnit) {
        int target = (int) distanceUnit.toPulses(distance);

        frontLeftMotor.setTargetPosition(frontLeftMotor.getCurrentPosition() + target);
        frontRightMotor.setTargetPosition(frontRightMotor.getCurrentPosition() - target);
        rearLeftMotor.setTargetPosition(rearLeftMotor.getCurrentPosition() - target);
        rearRightMotor.setTargetPosition(rearRightMotor.getCurrentPosition() + target);

        Arrays.stream(motors).forEach(motor -> motor.setMode(DcMotor.RunMode.RUN_TO_POSITION));

        autoDriveRobot(0, Constants.STRAFE_DRIVE_SPEED, 0);
        while (frontLeftMotor.isBusy() && frontRightMotor.isBusy() &&
                rearLeftMotor.isBusy() && rearRightMotor.isBusy()) { }
        autoDriveRobot(0, 0, 0);

        Arrays.stream(motors).forEach(motor -> motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER));
    }

    public void turnToHeading(double heading) {
        getSteeringCorrection(heading, Constants.P_DRIVE_GAIN);

        while(Math.abs(headingError) > Constants.HEADING_THRESHOLD) {
            double turn = getSteeringCorrection(heading, Constants.P_TURN_GAIN);
            autoDriveRobot(0, 0, turn);
        }
        autoDriveRobot(0, 0, 0);
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
