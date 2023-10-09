package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.DigitsCount.countDigits;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DigitsCountTest {
    @ParameterizedTest(name = "Iteration = #{index} -> Given number = {0}, Expected result = {1}")
    @CsvSource({
        "4666, 4",
        "544, 3",
        "0, 1",
        "34097850120456, 14",
        "-278346, 6"
    })
    @DisplayName("Тест подсчета количества цифр на разных значениях")
    public void countDigitsShouldWorkGivenDifferentArguments(long number, int expectedResult) {
        int result = countDigits(number);

        assertThat(result).isEqualTo(expectedResult);
    }
}
