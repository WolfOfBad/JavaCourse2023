package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LicensePlateCheckerTest {
    private static Arguments[] plates() {
        return new Arguments[] {
            Arguments.of("А123ВЕ777", true),
            Arguments.of("О777ОО177", true),
            Arguments.of("123АВЕ777", false),
            Arguments.of("А123ВГ77", false),
            Arguments.of("А123ВЕ7777", false)
        };
    }


    @ParameterizedTest
    @MethodSource("plates")
    public void plateCheckTest(String plate, boolean expected) {
        LicensePlateChecker obj = new LicensePlateChecker();

        boolean result = obj.check(plate);

        assertThat(result).isEqualTo(expected);
    }

}
