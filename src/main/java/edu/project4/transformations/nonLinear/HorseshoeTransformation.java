package edu.project4.transformations.nonLinear;

import edu.project4.records.Point;
import edu.project4.transformations.Transformation;

public class HorseshoeTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = radius(point);

        double x = (point.x() - point.y()) * (point.x() + point.y()) / radius;
        double y = 2 * point.x() * point.y() / radius;

        return new Point(x, y);
    }
}
