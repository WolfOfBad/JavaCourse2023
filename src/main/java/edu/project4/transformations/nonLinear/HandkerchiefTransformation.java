package edu.project4.transformations.nonLinear;

import edu.project4.records.Point;
import edu.project4.transformations.Transformation;

public class HandkerchiefTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = radius(point);
        double theta = theta(point);

        double x = radius * Math.sin(theta + radius);
        double y = radius * Math.cos(theta - radius);

        return new Point(x, y);
    }
}
