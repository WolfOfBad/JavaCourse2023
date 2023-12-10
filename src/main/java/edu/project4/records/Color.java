package edu.project4.records;

import java.util.Random;

public record Color(
    int r,
    int g,
    int b
) {
    private static final int BOUND = 256;
    private static final Random RANDOM = new Random();

    public static Color getRandom() {
        return new Color(
            RANDOM.nextInt(0, BOUND),
            RANDOM.nextInt(0, BOUND),
            RANDOM.nextInt(0, BOUND)
        );
    }

    public static Color mixColors(Color first, Color second) {
        return new Color(
            (first.r() + second.r()) / 2,
            (first.g() + second.g()) / 2,
            (first.b() + second.b()) / 2
        );
    }

}
