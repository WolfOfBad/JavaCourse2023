package edu.project4.transformations.nonLinear;

import edu.project4.records.Point;
import edu.project4.transformations.Transformation;

public class ExTransformation implements Transformation {
    @Override
    @SuppressWarnings("MagicNumber")
    public Point apply(Point point) {
        double theta = theta(point);
        double radius = radius(point);

        double p0 = Math.sin(theta + radius);
        double p1 = Math.cos(theta - radius);

        double x = radius * (Math.pow(p0, 3) + Math.pow(p1, 3));
        double y = radius * (Math.pow(p0, 3) - Math.pow(p1, 3));

        return new Point(x, y);
    }
}
