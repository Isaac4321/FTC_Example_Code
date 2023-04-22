package main.hardware;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LED;

public class ColorSensorImpl {
    private final ColorSensor colorSensor;

    public ColorSensorImpl(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
    }

    public void enable() {
        colorSensor.enableLed(true);
    }

    public void disable() {
        colorSensor.enableLed(false);
    }

    public int[] getColors() {
        return new int[]{colorSensor.red() * 255, colorSensor.green() * 255, colorSensor.blue() * 255};
    }


}
