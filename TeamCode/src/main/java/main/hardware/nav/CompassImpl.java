package main.hardware.nav;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
import com.qualcomm.robotcore.hardware.AccelerationSensor;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.MagneticFlux;

/**
 * An example implementation of the Modern Robotics Compass.
 * For more product info, visit <a href=https://modernroboticsinc.com/product/optical-distance-sensor/>Compass</a>
 */
public class CompassImpl {
    private final ModernRoboticsI2cCompassSensor compass;

    public CompassImpl(HardwareMap hardwareMap) {
        compass = hardwareMap.get(ModernRoboticsI2cCompassSensor.class, "compass");
    }

    public void calibrate() {
        compass.setMode(CompassSensor.CompassMode.CALIBRATION_MODE);
    }

    public double getHeading() {
        return compass.getDirection();
    }

    public MagneticFlux getMagneticFlux() {
        return compass.getMagneticFlux();
    }

    public Acceleration getAcceleration() {
        return compass.getAcceleration();
    }
}
