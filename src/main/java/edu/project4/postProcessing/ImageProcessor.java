package edu.project4.postProcessing;

import edu.project4.records.PixelMap;

@FunctionalInterface
public interface ImageProcessor {
    PixelMap process(PixelMap pixelMap);
}
