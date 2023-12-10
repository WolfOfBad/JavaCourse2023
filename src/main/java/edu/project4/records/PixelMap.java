package edu.project4.records;

import java.util.Map;

public record PixelMap(
    Map<Point, Pixel> pixelMap,
    ImageBorders borders
) {
}
