package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.KaprekarConstant.countK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KaprekarConstantTest {
    @ParameterizedTest(name = "Iteration #{index} -> Given number = {0}, Expected result = {1}")
    @CsvSource({
        "3524, 3",
        "6621, 5",
        "6554, 4",
        "1234, 3"
    })
    @DisplayName("Тест постоянной Капрекара на разных значениях")
    public void countKShouldWorkDifferentArguments(int number, int expectedResult) {
        int result = countK(number);

        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "Iteration #{index} -> Given number = {0}")
    @CsvSource({
        "1000",
        "10000",
        "123",
        "99999"
    })
    @DisplayName("Проверка постоянной Капрекара на выход из промежутка")
    public void countKCheckException(int number) {
        Exception thrown = assertThrows(Exception.class, () -> countK(number));

        assertThat(thrown).hasMessage("Number must be in interval from 1001 to 9999");
    }
}
