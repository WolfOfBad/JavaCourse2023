package edu.project4.records;

import edu.project4.transformations.RandomTransformationProvider;
import edu.project4.transformations.Transformation;

public record ColoredTransformation(
    Transformation transformation,
    Color color
) {
    public static ColoredTransformation getRandom() {
        return new ColoredTransformation(
            RandomTransformationProvider.getRandom(),
            Color.getRandom()
        );
    }
}
