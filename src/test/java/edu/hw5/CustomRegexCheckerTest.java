package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomRegexCheckerTest {
    private static Arguments[] args1() {
        return new Arguments[] {
            Arguments.of("1101", true),
            Arguments.of("000", true),
            Arguments.of("00", false),
            Arguments.of("111", false),
            Arguments.of("1102", false)
        };
    }

    @ParameterizedTest
    @MethodSource("args1")
    @DisplayName("Тест первого условия")
    public void test1(String string, boolean expected) {
        CustomRegexChecker obj = new CustomRegexChecker();

        boolean result = obj.task1(string);

        assertThat(result).isEqualTo(expected);
    }

    private static Arguments[] args2() {
        return new Arguments[] {
            Arguments.of("101", true),
            Arguments.of("100", false),
            Arguments.of("121", false)
        };
    }

    @ParameterizedTest
    @MethodSource("args2")
    @DisplayName("Тест второго условия")
    public void test2(String string, boolean expected) {
        CustomRegexChecker obj = new CustomRegexChecker();

        boolean result = obj.task2(string);

        assertThat(result).isEqualTo(expected);
    }

    private static Arguments[] args3() {
        return new Arguments[] {
            Arguments.of("101", true),
            Arguments.of("1", true),
            Arguments.of("121", false),
            Arguments.of("", false),
        };
    }

    @ParameterizedTest
    @MethodSource("args3")
    @DisplayName("Тест третьего условия")
    public void test3(String string, boolean expected) {
        CustomRegexChecker obj = new CustomRegexChecker();

        boolean result = obj.task3(string);

        assertThat(result).isEqualTo(expected);
    }
}
