package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.CyclicShift.rotateLeft;
import static edu.hw1.CyclicShift.rotateRight;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CyclicShiftTest {
    @ParameterizedTest(name = "#{index} -> Given number = {0}, Shift = {1}, Expected result = {2}")
    @CsvSource({
        "8, 1, 4",
        "20, 7, 5",
    })
    @DisplayName("Тест сдвига вправо на разных значениях")
    public void rightShiftShouldWorkGivenDifferentArguments(int number, int shift, int expectedResult) {
        int result = rotateRight(number, shift);

        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "Iteration #{index} -> Given number = {0}, Shift = {1}, Expected result = {2}")
    @CsvSource({
        "16, 1, 1",
        "17, 2, 6",
    })
    @DisplayName("Тест сдвига влево на разных значениях")
    public void leftShiftShouldWorkGivenDifferentArguments(int number, int shift, int expectedResult) {
        int result = rotateLeft(number, shift);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Тест сдвига отрицательного числа влево")
    public void negativeNumberLeftShift() {
        // given
        int number = -1;
        int shift = 1;

        // when
        Exception thrown = assertThrows(Exception.class, () -> rotateLeft(number, shift));

        // then
        assertThat(thrown).hasMessage("You cannot use negative numbers");
    }

    @Test
    @DisplayName("Тест сдвига отрицательного числа вправо")
    public void negativeNumberRightShift() {
        // given
        int number = -1;
        int shift = 1;

        // when
        Exception thrown = assertThrows(Exception.class, () -> rotateRight(number, shift));

        // then
        assertThat(thrown).hasMessage("You cannot use negative numbers");
    }

}
