package edu.project4.transformations.nonLinear;

import edu.project4.records.Point;
import edu.project4.transformations.Transformation;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = radius(point);
        double theta = theta(point);

        double x = radius * Math.sin(radius * theta);
        double y = radius * -Math.cos(radius * theta);

        return new Point(x, y);
    }
}
