package edu.hw7;

import java.math.BigInteger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class FactorialCounterTest {
    private static Arguments[] args() {
        return new Arguments[] {
            Arguments.of(1, BigInteger.valueOf(1)),
            Arguments.of(2, BigInteger.valueOf(2)),
            Arguments.of(3, BigInteger.valueOf(6)),
            Arguments.of(4, BigInteger.valueOf(24)),
            Arguments.of(5, BigInteger.valueOf(120)),
            Arguments.of(6, BigInteger.valueOf(720))
        };
    }

    @ParameterizedTest
    @MethodSource("args")
    public void factorialTest(long value, BigInteger expected) {
        assertThat(FactorialCounter.getFactorial(value)).isEqualTo(expected);
    }
}
