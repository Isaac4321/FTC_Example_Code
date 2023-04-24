package main.hardware.sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;

/**
 * An example implementation of the Modern Robotics IR Locator 360 (IR Sensor).
 * For more product info, visit <a href=https://modernroboticsinc.com/product/ir-locator-360/>IR Locator 360</a>
 */
public class IRSensorImpl {
    private final IrSeekerSensor irSensor;

    public IRSensorImpl(HardwareMap hardwareMap) {
        irSensor = hardwareMap.get(IrSeekerSensor.class, "irSensor");
    }

    public double getAngle() {
        return irSensor.getAngle();
    }

    public double getStrength() {
        return irSensor.getStrength();
    }
}
