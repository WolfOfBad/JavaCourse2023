package edu.project4.render;

import edu.project4.records.Color;
import edu.project4.records.ColoredTransformation;
import edu.project4.records.GenerationBorders;
import edu.project4.records.ImageBorders;
import edu.project4.records.Pixel;
import edu.project4.records.PixelMap;
import edu.project4.records.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SingleThreadRenderer implements Renderer {
    private static final int PRE_ITERATIONS = 20;
    private static final Random RANDOM = ThreadLocalRandom.current();

    @Override
    public PixelMap render(
        ImageBorders imageBorders,
        GenerationBorders generationBorders,
        List<ColoredTransformation> transformations,
        int samples,
        int iterations
    ) {
        double newX;
        double newY;
        Map<Point, Pixel> pixelMap = new HashMap<>();

        for (int i = 0; i < samples; i++) {
            newX = RANDOM.nextDouble(generationBorders.xMin(), generationBorders.xMax());
            newY = RANDOM.nextDouble(generationBorders.yMin(), generationBorders.yMax());

            for (int step = -PRE_ITERATIONS; step < iterations; step++) {
                ColoredTransformation transformation = transformations.get(RANDOM.nextInt(transformations.size()));

                Point newPoint = transformation.transformation().apply(new Point(newX, newY));
                newX = newPoint.x();
                newY = newPoint.y();

                if (addCondition(step, newPoint, generationBorders)) {
                    int x = scale(newX, imageBorders.width(), generationBorders.xMin(), generationBorders.xMax());
                    int y = scale(newY, imageBorders.height(), generationBorders.yMin(), generationBorders.yMax());

                    if (x < imageBorders.width() && y < imageBorders.height()) {
                        Point point = new Point(x, y);
                        if (!pixelMap.containsKey(point)) {
                            pixelMap.put(point, new Pixel(transformation.color(), 1));
                        } else {
                            Pixel old = pixelMap.get(point);
                            pixelMap.put(
                                point,
                                new Pixel(Color.mixColors(old.color(), transformation.color()), old.hitCount() + 1)
                            );
                        }
                    }
                }
            }

        }

        return new PixelMap(
            pixelMap,
            imageBorders
        );
    }

    private boolean addCondition(int step, Point newPoint, GenerationBorders borders) {
        return step >= 0
            && borders.xMin() <= newPoint.x() && newPoint.x() <= borders.xMax()
            && borders.yMin() <= newPoint.y() && newPoint.y() <= borders.yMax();
    }

    private int scale(double point, int size, double min, double max) {
        return size - (int) Math.round((max - point) / (max - min) * size);
    }
}
