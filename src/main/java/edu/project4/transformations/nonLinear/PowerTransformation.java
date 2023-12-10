package edu.project4.transformations.nonLinear;

import edu.project4.records.Point;
import edu.project4.transformations.Transformation;

public class PowerTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double theta = theta(point);
        double radius = Math.pow(radius(point), Math.sin(theta));


        double x = radius * Math.cos(theta);
        double y = radius * Math.sin(theta);

        return new Point(x, y);
    }
}
