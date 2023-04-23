package main.hardware;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * An example implementation of the Rev Robotics Magnetic Limit Switch.
 * For more product info, visit <a href=https://www.revrobotics.com/rev-31-1462/>Magnetic Limit Switch</a>
 */
public class LimitSwitchImpl {
    private final DigitalChannel limitSwitch;

    /** Initializes the limit switch, make sure to have the limit switch mapped in your configuration. */
    public LimitSwitchImpl(HardwareMap hardwareMap) {
        limitSwitch = hardwareMap.get(DigitalChannel.class, "limitSwitch");
        limitSwitch.setMode(DigitalChannel.Mode.OUTPUT);
    }

    /** Return whether or not the limit switch has been pressed. */
    public boolean isPressed() {
        return limitSwitch.getState();
    }

}
