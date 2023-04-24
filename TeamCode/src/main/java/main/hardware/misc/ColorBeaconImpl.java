package main.hardware.misc;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImplOnSimple;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchSimple;

/**
 * An example implementation of the Modern Robotics Color Beacon.
 * For more product info, visit <a href=https://modernroboticsinc.com/product/color-beacon/>Color Beacon</a>
 */
public class ColorBeaconImpl {
    private byte[] colorBeaconCache;

    private final I2cDeviceSynchSimple colorBeacon;
    private final I2cDeviceSynch colorBeaconReader;

    public ColorBeaconImpl(HardwareMap hardwareMap, String deviceName) {
        colorBeacon = hardwareMap.get(I2cDeviceSynchSimple.class, deviceName);
        colorBeaconReader = new I2cDeviceSynchImplOnSimple(colorBeacon, false);
    }

    public void on() {
        colorBeaconReader.engage();
    }

    public void off() {
        colorBeaconReader.write8(4, 0);
    }

    public void display(Color color) {
        colorBeaconReader.write8(color.a, color.b);
    }

    public void rgbDisplay(int red, int green, int blue) {
        colorBeaconReader.write8(4, 8);
        colorBeaconReader.write8(5, red);
        colorBeaconReader.write8(6, green);
        colorBeaconReader.write8(7, blue);
    }

    public Color getColor() {
        colorBeaconCache = colorBeaconReader.read(0x04, 4);

        switch (colorBeaconCache[0]) {
            case 0:
                return Color.OFF;
            case 1:
                return Color.RED;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.BLUE;
            case 5:
                return Color.PURPLE;
            case 6:
                return Color.TEAL;
            case 7:
                return Color.WHITE;
            case 8:
                return Color.CUSTOM;
            default:
                return null;
        }
    }

    enum Color {
        CUSTOM(0, 0),
        OFF(4, 0),
        RED(4, 1),
        GREEN(4, 2),
        YELLOW(4, 3),
        BLUE(4, 4),
        PURPLE(4, 5),
        TEAL(4, 6),
        WHITE(4, 7);

        private int a;
        private int b;
        Color(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }

}
