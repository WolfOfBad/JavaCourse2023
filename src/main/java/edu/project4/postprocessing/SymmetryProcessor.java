package edu.project4.postprocessing;

import edu.project4.records.Color;
import edu.project4.records.ImageBorders;
import edu.project4.records.Pixel;
import edu.project4.records.PixelMap;
import edu.project4.records.Point;
import java.util.HashMap;
import java.util.Map;

public class SymmetryProcessor implements ImageProcessor {
    private final int symmetryCount;

    public SymmetryProcessor(int symmetryCount) {
        this.symmetryCount = symmetryCount;
    }

    @Override
    public PixelMap process(PixelMap pixelMap) {
        Map<Point, Pixel> result = new HashMap<>();

        for (var entry : pixelMap.pixelMap().entrySet()) {
            for (int i = 0; i < symmetryCount; i++) {
                double angle = 2 * Math.PI / symmetryCount * i;

                Point rotated = rotatePoint(
                    entry.getKey(),
                    angle,
                    pixelMap.borders()
                );
                if (0 <= rotated.x() && rotated.x() < pixelMap.borders().width()
                    && 0 <= rotated.y() && rotated.y() < pixelMap.borders().height()) {
                    if (result.containsKey(rotated)) {
                        result.put(
                            rotated,
                            new Pixel(
                                Color.mixColors(result.get(rotated).color(), entry.getValue().color()),
                                result.get(rotated).hitCount() + entry.getValue().hitCount()
                            )
                        );
                    } else {
                        result.put(rotated, entry.getValue());
                    }
                }
            }
        }
        return new PixelMap(result, pixelMap.borders());
    }

    private Point rotatePoint(Point point, double angle, ImageBorders borders) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        int centerX = borders.width() / 2;
        int centerY = borders.height() / 2;

        int newX = (int) ((point.x() - centerX) * cos - (point.y() - centerY) * sin + centerX);
        int newY = (int) ((point.x() - centerX) * sin + (point.y() - centerY) * cos + centerY);

        return new Point(newX, newY);
    }

}
