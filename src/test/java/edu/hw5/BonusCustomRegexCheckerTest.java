package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BonusCustomRegexCheckerTest {
    private static Arguments[] args1() {
        return new Arguments[] {
            Arguments.of("101", true),
            Arguments.of("10", false),
            Arguments.of("121", false),
        };
    }

    @ParameterizedTest
    @MethodSource("args1")
    @DisplayName("Тест первого задания")
    public void test1(String string, boolean expected) {
        BonusCustomRegexChecker obj = new BonusCustomRegexChecker();

        boolean result = obj.task1(string);

        assertThat(result).isEqualTo(expected);
    }

    private static Arguments[] args2() {
        return new Arguments[] {
            Arguments.of("01011", true),
            Arguments.of("1101", true),
            Arguments.of("0101", false),
            Arguments.of("11011", false),
            Arguments.of("01211", false),
            Arguments.of("1121", false),
        };
    }

    @ParameterizedTest
    @MethodSource("args2")
    @DisplayName("Тест второго задания")
    public void test2(String string, boolean expected) {
        BonusCustomRegexChecker obj = new BonusCustomRegexChecker();

        boolean result = obj.task2(string);

        assertThat(result).isEqualTo(expected);
    }

    private static Arguments[] args3() {
        return new Arguments[] {
            Arguments.of("", true),
            Arguments.of("000", true),
            Arguments.of("1101111011101111", true),
            Arguments.of("0", false),
            Arguments.of("010", false)
        };
    }

    @ParameterizedTest
    @MethodSource("args3")
    @DisplayName("Тест третьего задания")
    public void test3(String string, boolean expected) {
        BonusCustomRegexChecker obj = new BonusCustomRegexChecker();

        boolean result = obj.task3(string);

        assertThat(result).isEqualTo(expected);
    }

    private static Arguments[] args4() {
        return new Arguments[] {
            Arguments.of("", true),
            Arguments.of("0", true),
            Arguments.of("01", true),
            Arguments.of("110", true),
            Arguments.of("1110", true),
            Arguments.of("11", false),
            Arguments.of("111", false),
        };
    }

    @ParameterizedTest
    @MethodSource("args4")
    @DisplayName("Тест четвертого задания")
    public void test4(String string, boolean expected) {
        BonusCustomRegexChecker obj = new BonusCustomRegexChecker();

        boolean result = obj.task4(string);

        assertThat(result).isEqualTo(expected);
    }

    private static Arguments[] args5CountFrom0() {
        return new Arguments[] {
            Arguments.of("0101010", true),
            Arguments.of("0100010", false)
        };
    }

    @ParameterizedTest
    @MethodSource("args5CountFrom0")
    @DisplayName("Тест пятого задания. Индексация с 0")
    public void test5CountFrom0(String string, boolean expected) {
        BonusCustomRegexChecker obj = new BonusCustomRegexChecker();

        boolean result = obj.task5CountFrom0(string);

        assertThat(result).isEqualTo(expected);
    }

    private static Arguments[] args5CountFrom1() {
        return new Arguments[] {
            Arguments.of("10101", true),
            Arguments.of("10001", false)
        };
    }

    @ParameterizedTest
    @MethodSource("args5CountFrom1")
    @DisplayName("Тест пятого задания. Индексация с 1")
    public void test5(String string, boolean expected) {
        BonusCustomRegexChecker obj = new BonusCustomRegexChecker();

        boolean result = obj.task5CountFrom1(string);

        assertThat(result).isEqualTo(expected);
    }

    private static Arguments[] args6() {
        return new Arguments[] {
            Arguments.of("0000", true),
            Arguments.of("010", true),
            Arguments.of("10000", true),
            Arguments.of("", false),
            Arguments.of("01", false),
            Arguments.of("0101", false)
        };
    }

    @ParameterizedTest
    @MethodSource("args6")
    @DisplayName("Тест шестого задания")
    public void test6(String string, boolean expected) {
        BonusCustomRegexChecker obj = new BonusCustomRegexChecker();

        boolean result = obj.task6(string);

        assertThat(result).isEqualTo(expected);
    }

    private static Arguments[] args7() {
        return new Arguments[] {
            Arguments.of("", true),
            Arguments.of("010101", true),
            Arguments.of("1100", false),
            Arguments.of("011", false),
            Arguments.of("0110", false),
        };
    }

    @ParameterizedTest
    @MethodSource("args7")
    @DisplayName("Тест седьмого задания")
    public void test7(String string, boolean expected) {
        BonusCustomRegexChecker obj = new BonusCustomRegexChecker();

        boolean result = obj.task7(string);

        assertThat(result).isEqualTo(expected);
    }
}
