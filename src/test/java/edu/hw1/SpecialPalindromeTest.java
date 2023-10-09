package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.SpecialPalindrome.isPalindromeDescendant;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SpecialPalindromeTest {
    @ParameterizedTest(name = "#{index} -> Number = {0}, Expected result = {1}")
    @CsvSource({
        "11211230, true",
        "13001120, true",
        "23336014, true",
        "11, true",
        "113, false",
        "124, false"
    })
    @DisplayName("Тест особого палиндрома на разных значениях")
    public void isPalindromeDescendantShouldWorkDifferentArguments(int number, boolean expectedResult) {
        boolean result = isPalindromeDescendant(number);

        assertThat(result).isEqualTo(expectedResult);
    }
}
