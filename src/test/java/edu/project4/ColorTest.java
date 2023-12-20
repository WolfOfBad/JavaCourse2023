package edu.project4;

import edu.project4.records.Color;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ColorTest {
    @Test
    public void mixColorsTest() {
        Color first = new Color(100, 100, 100);
        Color second = new Color(200, 200, 200);

        Color mixed = Color.mixColors(first, second);

        assertThat(mixed).isEqualTo(new Color(150, 150, 150));
    }

    @Test
    public void getRandomTest() {
        Color color = Color.getRandom();

        assertThat(color).isNotNull();
    }

}
