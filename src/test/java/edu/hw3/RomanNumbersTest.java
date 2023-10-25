package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RomanNumbersTest {
    private static Arguments[] numbers() {
        return new Arguments[] {
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(3999, "MMMCMXCIX"),
            Arguments.of(4000, null)
        };
    }

    @ParameterizedTest
    @MethodSource("numbers")
    public void convertToRomanTest(int number, String expected) {
        RomanNumbers obj = new RomanNumbers();
        String result = obj.convertToRoman(number);

        assertThat(result).isEqualTo(expected);
    }

}
