package edu.project4.transformations.nonLinear;

import edu.project4.records.Point;
import edu.project4.transformations.Transformation;

public class DiamondTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double radius = radius(point);
        double theta = theta(point);

        double x = Math.sin(theta) * Math.cos(radius);
        double y = Math.cos(theta) * Math.sin(radius);

        return new Point(x, y);
    }
}
