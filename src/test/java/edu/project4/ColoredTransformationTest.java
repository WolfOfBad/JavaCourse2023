package edu.project4;

import edu.project4.records.ColoredTransformation;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ColoredTransformationTest {
    @Test
    public void getRandomTest() {
        ColoredTransformation transformation = ColoredTransformation.getRandom();

        assertThat(transformation).isNotNull();
    }

}
