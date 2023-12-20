package edu.project4.transformations;

import edu.project4.records.Point;
import java.util.Random;

public class LinearTransformation implements Transformation {
    private static final Random RANDOM = new Random();
    private static final int BORDER = 2;
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;

    public LinearTransformation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public static LinearTransformation getRandom() {
        double a;
        double b;
        double c;
        double d;
        double e;
        double f;

        do {
            do {
                a = RANDOM.nextDouble(-1, 1);
                d = RANDOM.nextDouble(a * a, 1);

                if (RANDOM.nextBoolean()) {
                    d = -d;
                }
            } while ((a * a + d * d) > 1);
            do {
                b = RANDOM.nextDouble(-1, 1);
                e = RANDOM.nextDouble(b * b, 1);

                if (RANDOM.nextBoolean()) {
                    e = -e;
                }
            } while ((b * b + e * e) > 1);
        } while ((a * a + b * b + d * d + e * e)
            > (1 + (a * e - d * b) * (a * e - d * b)));

        c = RANDOM.nextDouble(-BORDER, BORDER);
        f = RANDOM.nextDouble(-BORDER, BORDER);

        return new LinearTransformation(a, b, c, d, e, f);
    }

    @Override
    public Point apply(Point point) {
        double x = a * point.x() + b * point.y() + c;
        double y = d * point.x() + e * point.y() + f;

        return new Point(x, y);
    }
}
