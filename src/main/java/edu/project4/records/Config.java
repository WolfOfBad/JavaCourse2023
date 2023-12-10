package edu.project4.records;

import edu.project4.ImageFormat;

public record Config(
    int height,
    int width,
    double xMin,
    double xMax,
    double yMin,
    double yMax,
    int samples,
    int iterations,
    int randomAffineTransformations,
    ColoredTransformation[] presetTransformations,
    ImageFormat format,
    boolean correction,
    String directory,
    String filename,
    double gamma,
    int threads,
    int symmetry
) {
}
