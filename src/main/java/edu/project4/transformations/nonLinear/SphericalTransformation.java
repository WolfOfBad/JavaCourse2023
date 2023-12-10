package edu.project4.transformations.nonLinear;

import edu.project4.records.Point;
import edu.project4.transformations.Transformation;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = radius(point);

        double x = point.x() / (radius * radius);
        double y = point.y() / (radius * radius);

        return new Point(x, y);
    }
}
