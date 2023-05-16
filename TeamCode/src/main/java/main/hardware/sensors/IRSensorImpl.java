package main.hardware.sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;

/**
 * An example implementation of the Modern Robotics IR Locator 360 (IR Sensor).
 * For more product info, visit <a href=https://modernroboticsinc.com/product/ir-locator-360/>IR Locator 360</a>
 */
public class IRSensorImpl {
    private final IrSeekerSensor irSensor;

    /** Initializes the IR sensor, make sure to have the IR sensor mapped in your robot configuration. */
    public IRSensorImpl(HardwareMap hardwareMap) {
        irSensor = hardwareMap.get(IrSeekerSensor.class, "irSensor");
    }

    /** Returns the estimated angle of which the IR signal is coming from. 0 if no IR signal is detected. */
    public double getAngle() {
        return irSensor.getAngle();
    }

    /** Returns the strength of the IR signal. [0, 1] */
    public double getStrength() {
        return irSensor.getStrength();
    }
}
