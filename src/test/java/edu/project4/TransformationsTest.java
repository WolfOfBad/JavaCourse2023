package edu.project4;

import edu.project4.records.Point;
import edu.project4.transformations.LinearTransformation;
import edu.project4.transformations.RandomTransformationProvider;
import edu.project4.transformations.Transformation;
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
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class TransformationsTest {
    public static Arguments[] transformations() {
        return new Arguments[] {
            Arguments.of(LinearTransformation.getRandom()),
            Arguments.of(new CylinderTransformation()),
            Arguments.of(new DiamondTransformation()),
            Arguments.of(new DiscTransformation()),
            Arguments.of(new ExponentialTransformation()),
            Arguments.of(new ExTransformation()),
            Arguments.of(new FisheyeTransformation()),
            Arguments.of(new HandkerchiefTransformation()),
            Arguments.of(new HeartTransformation()),
            Arguments.of(new HorseshoeTransformation()),
            Arguments.of(new HyperbolicTransformation()),
            Arguments.of(new PowerTransformation()),
            Arguments.of(new SphericalTransformation()),
            Arguments.of(new SpiralTransformation()),
            Arguments.of(new SwirlTransformation())
        };
    }

    @ParameterizedTest
    @MethodSource("transformations")
    public void notNullTest(Transformation transformation) {
        Point point = new Point(1, 1);

        point = transformation.apply(point);

        assertThat(point).isNotNull();
    }

    @RepeatedTest(50)
    public void providerTest() {
        Transformation transformation = RandomTransformationProvider.getRandom();

        assertThat(transformation).isNotNull();
    }

}
