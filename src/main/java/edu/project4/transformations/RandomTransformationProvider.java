package edu.project4.transformations;

import edu.project4.transformations.nonLinear.CylinderTransformation;
import edu.project4.transformations.nonLinear.DiamondTransformation;
import edu.project4.transformations.nonLinear.DiscTransformation;
import edu.project4.transformations.nonLinear.ExTransformation;
import edu.project4.transformations.nonLinear.ExponentialTransformation;
import edu.project4.transformations.nonLinear.FisheyeTransformation;
import edu.project4.transformations.nonLinear.HandkerchiefTransformation;
import edu.project4.transformations.nonLinear.HeartTransformation;
import edu.project4.transformations.nonLinear.HorseshoeTransformation;
import edu.project4.transformations.nonLinear.HyperbolicTransformation;
import edu.project4.transformations.nonLinear.PowerTransformation;
import edu.project4.transformations.nonLinear.SphericalTransformation;
import edu.project4.transformations.nonLinear.SpiralTransformation;
import edu.project4.transformations.nonLinear.SwirlTransformation;
import java.util.List;
import java.util.Random;

public final class RandomTransformationProvider {
    private RandomTransformationProvider() {
    }

    private static final Random RANDOM = new Random();
    private static final List<Transformation> transformations = List.of(
        new CylinderTransformation(),
        new DiamondTransformation(),
        new DiscTransformation(),
        new ExponentialTransformation(),
        new ExTransformation(),
        new FisheyeTransformation(),
        new HandkerchiefTransformation(),
        new HeartTransformation(),
        new HorseshoeTransformation(),
        new HyperbolicTransformation(),
        new PowerTransformation(),
        new SphericalTransformation(),
        new SpiralTransformation(),
        new SwirlTransformation()
    );

    public static Transformation getRandom() {
        int index = RANDOM.nextInt(0, transformations.size() + 1);
        if (index == transformations.size()) {
            return LinearTransformation.getRandom();
        }
        return transformations.get(index);
    }
}
