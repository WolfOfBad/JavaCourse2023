package edu.hw11;

import edu.hw11.dynamicchange.ArithmeticUtils;
import edu.hw11.dynamicchange.DynamicChanger;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DynamicChangeTest {
    // Этот тест работает при обычном запуске и нормально меняет поведение метода
    // но во время билда на гитхабе из за каких то конфликтов с jacoco падает из за ошибки
    // java.lang.UnsupportedOperationException: class redefinition failed: attempted to delete a method
    @Test
    public void test() {
        int result1 = ArithmeticUtils.sum(3, 4);
        DynamicChanger.change();
        int result2 = ArithmeticUtils.sum(3, 4);

        System.out.println(result1);
        System.out.println(result2);

        assertThat(result1).isEqualTo(7);
        assertThat(result2).isEqualTo(12);
    }
}
