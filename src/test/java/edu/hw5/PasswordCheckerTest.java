package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordCheckerTest {
    // ~ ! @ # $ % ^ & * |
    private static Arguments[] passwords() {
        return new Arguments[] {
            Arguments.of("~", true),
            Arguments.of("!", true),
            Arguments.of("@", true),
            Arguments.of("#", true),
            Arguments.of("%", true),
            Arguments.of("^", true),
            Arguments.of("&", true),
            Arguments.of("*", true),
            Arguments.of("|", true),
            Arguments.of("some password", false)
        };
    }

    @ParameterizedTest
    @MethodSource("passwords")
    public void passwordCheckTest(String password, boolean expected) {
        PasswordChecker obj = new PasswordChecker();

        boolean result = obj.check(password);

        assertThat(result).isEqualTo(expected);
    }

}
