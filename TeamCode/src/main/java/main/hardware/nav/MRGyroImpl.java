package main.hardware.nav;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * An example implementation of the Modern Robotics Gyro.
 * For more product info, visit <a href=https://modernroboticsinc.com/product/integrating-gyro/>Gyro</a>
 */
public class MRGyroImpl {
    private final ModernRoboticsI2cGyro gyro;

    public MRGyroImpl(HardwareMap hardwareMap) {
        gyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
    }

    public void calibrate() {
        gyro.calibrate();
    }

    public double getXAngle() {
        return gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYX, AngleUnit.DEGREES).firstAngle;
    }

    public double getYAngle() {
        return gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES).firstAngle;
    }

    public double getZAngle() {
        return gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }
}
