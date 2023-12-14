package edu.hw11;

import edu.hw11.dynamicChange.ArithmeticUtils;
import edu.hw11.dynamicChange.DynamicChanger;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DynamicChangeTest {
    @Test
    public void t() {
        int result1 = ArithmeticUtils.sum(3, 4);
        DynamicChanger.change();
        int result2 = ArithmeticUtils.sum(3, 4);

        assertThat(result1).isEqualTo(7);
        assertThat(result2).isEqualTo(12);
    }
}
