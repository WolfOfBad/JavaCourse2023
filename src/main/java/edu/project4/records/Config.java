package edu.project4.records;

import edu.project4.ImageFormat;
import java.nio.file.Path;

public record Config(
    ImageBorders imageBorders,
    GenerationBorders generationBorders,
    int samples,
    int iterations,
    int randomAffineTransformations,
    ColoredTransformation[] presetTransformations,
    ImageFormat format,
    boolean correction,
    Path directory,
    String filename,
    double gamma,
    int threads,
    int symmetry
) {
}
