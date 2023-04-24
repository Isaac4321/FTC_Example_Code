package main.hardware.misc;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

/**
 * An example implementation of the Rev Robotics Blinkin.
 * For more product info, visit <a href=https://www.revrobotics.com/rev-11-1105/>Blinkin LED Driver</a>
 */
public class RevBlinkinImpl {
    private final RevBlinkinLedDriver blinkin;

    /** Initializes the blinkin, make sure to have the blinkin mapped in your configuration. */
    public RevBlinkinImpl(HardwareMap hardwareMap) {
        ServoControllerEx servoController = hardwareMap.get(ServoControllerEx.class, "blinkinServoController");
        blinkin = new RevBlinkinLedDriver(servoController, 0); // Port: look on control/expansion hub for port #.
    }

    /** Sets the LEDs to a specific pattern. A few example patterns are as follows:
     *  - RED: Solid colour red
     *  - LIME: Solid colour lime
     *  - CONFETTI: Rainbow fun-ness.
     */
    public void setColour(RevBlinkinLedDriver.BlinkinPattern pattern) {
        blinkin.setPattern(pattern);
    }
}
