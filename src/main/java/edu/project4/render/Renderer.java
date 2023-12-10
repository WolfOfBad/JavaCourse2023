package edu.project4.render;

import edu.project4.records.ColoredTransformation;
import edu.project4.records.GenerationBorders;
import edu.project4.records.ImageBorders;
import edu.project4.records.PixelMap;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    PixelMap render(
        ImageBorders imageBorders,
        GenerationBorders generationBorders,
        List<ColoredTransformation> transformations,
        int samples,
        int iterations
    );
}
