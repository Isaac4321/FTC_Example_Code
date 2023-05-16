package main.util.vision;

public class CameraConstants {
    public static class MicrosoftLifecamHD3000v1 {
        private final static int[] SIZE = new int[]{640, 480};
        private final static float[] FOCAL_LENGTH = new float[]{678.154f, 678.17f};
        private final static float[] PRINCIPAL_POINT = new float[]{318.135f, 228.374f};
        private final static float[] DISTORTION_COEFF = new float[]{
                0.154576f,
                -1.19143f,
                0f,
                0f,
                2.06105f,
                0f,
                0f,
                0f
        };
    }
}
