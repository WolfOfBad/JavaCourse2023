package edu.project4.postprocessing;

import edu.project4.records.Color;
import edu.project4.records.Pixel;
import edu.project4.records.PixelMap;
import edu.project4.records.Point;
import java.util.HashMap;
import java.util.Map;

public class GammaCorrector implements ImageProcessor {
    private final double gamma;

    public GammaCorrector(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public PixelMap process(PixelMap pixelMap) {
        double max = 0.0;
        Map<Point, Double> normals = new HashMap<>();

        for (var entry : pixelMap.pixelMap().entrySet()) {
            double normal = Math.log10(entry.getValue().hitCount());
            if (normal > max) {
                max = normal;
            }
            normals.put(entry.getKey(), normal);
        }

        for (var entry : pixelMap.pixelMap().entrySet()) {
            Pixel pixel = entry.getValue();
            Color color = pixel.color();
            double normal = normals.get(entry.getKey()) / max;

            Pixel newPixel = new Pixel(
                new Color(
                    (int) Math.round(color.r() * Math.pow(normal, (1.0 / gamma))),
                    (int) Math.round(color.g() * Math.pow(normal, (1.0 / gamma))),
                    (int) Math.round(color.b() * Math.pow(normal, (1.0 / gamma)))
                ),
                pixel.hitCount()
            );

            pixelMap.pixelMap().put(entry.getKey(), newPixel);
        }

        return pixelMap;
    }
}
