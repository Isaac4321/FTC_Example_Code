package main.hardware.nav;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
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

    /** Initializes the Compass, make sure to have the compass mapped in your robot configuration. */
    public CompassImpl(HardwareMap hardwareMap) {
        compass = hardwareMap.get(ModernRoboticsI2cCompassSensor.class, "compass");
    }

    /** Puts the compass in calibration mode, follow the instructions to calibrate. */
    public void calibrate() {
        compass.setMode(CompassSensor.CompassMode.CALIBRATION_MODE);
    }

    /** Returns the current heading of the compass, eg: NW -> -45, NE -> 45. */
    public double getHeading() {
        return compass.getDirection();
    }

    /** Returns an instance of MagneticFlux which represents a 3 dimensional strength vector. */
    public MagneticFlux getMagneticFlux() {
        return compass.getMagneticFlux();
    }

    /** Returns the current acceleration the compass is reading. */
    public Acceleration getAcceleration() {
        return compass.getAcceleration();
    }
}
