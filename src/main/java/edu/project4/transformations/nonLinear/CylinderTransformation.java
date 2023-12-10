package edu.project4.transformations.nonLinear;

import edu.project4.records.Point;
import edu.project4.transformations.Transformation;

public class CylinderTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = Math.sin(point.x());
        double y = point.y();

        return new Point(x, y);
    }
}
