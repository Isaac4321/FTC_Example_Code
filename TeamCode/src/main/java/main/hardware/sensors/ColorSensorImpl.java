package main.hardware.sensors;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * An example implementation of the Rev Robotics Color Sensor v3.
 * For more product info, visit <a href=https://www.revrobotics.com/rev-31-1557/>Color Sensor v3</a>
 */
public class ColorSensorImpl {
    private final ColorSensor colorSensor;

    /** Initializes the color sensor, make sure to have the color sensor mapped in your robot configuration. */
    public ColorSensorImpl(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
    }

    /** Enables the built-in LED on the color sensor, does not need to be enabled to read colour. */
    public void enable() {
        colorSensor.enableLed(true);
    }

    /** Disables the built-in LED on the color sensor, does not need to be disabled to read colour. */
    public void disable() {
        colorSensor.enableLed(false);
    }

    /** Return an array of the scaled color values (0-255) in the following order: red, green, blue. */
    public int[] getColors() {
        return new int[]{colorSensor.red() * 255, colorSensor.green() * 255, colorSensor.blue() * 255};
    }

    /** Returns the scaled (0-255) red value from the sensor. */
    public int getRed() {
        return colorSensor.red() * 255;
    }

    /** Returns the scaled (0-255) green value from the sensor. */
    public int getGreen() {
        return colorSensor.green() * 255;
    }

    /** Returns the scaled (0-255) blue value from the sensor. */
    public int getBlue() {
        return colorSensor.blue() * 255;
    }




}
