package edu.project4.postprocessing;

import edu.project4.records.PixelMap;

@FunctionalInterface
public interface ImageProcessor {
    PixelMap process(PixelMap pixelMap);
}
