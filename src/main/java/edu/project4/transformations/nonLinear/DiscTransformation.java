package edu.project4.transformations.nonLinear;

import edu.project4.records.Point;
import edu.project4.transformations.Transformation;

public class DiscTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = radius(point);
        double theta = theta(point);

        double x = theta * Math.sin(Math.PI * radius) / Math.PI;
        double y = theta * Math.cos(Math.PI * radius) / Math.PI;

        return new Point(x, y);
    }
}
