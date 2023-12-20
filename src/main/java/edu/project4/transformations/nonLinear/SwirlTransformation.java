package edu.project4.transformations.nonLinear;

import edu.project4.records.Point;
import edu.project4.transformations.Transformation;

public class SwirlTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = radius(point);

        double x = point.x() * Math.sin(radius * radius) - point.y() * Math.cos(radius * radius);
        double y = point.x() * Math.cos(radius * radius) + point.y() * Math.sin(radius * radius);

        return new Point(x, y);
    }
}
