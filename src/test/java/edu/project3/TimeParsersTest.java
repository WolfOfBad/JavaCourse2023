package edu.project3;

import edu.project3.Parsers.TimeParsers.TimeParsersChain;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TimeParsersTest {
    private static Arguments[] times() {
        return new Arguments[] {
            Arguments.of("2023-11-19", OffsetDateTime.of(
                2023, 11, 19, 0, 0, 0, 0, ZoneOffset.UTC)),
            Arguments.of("19/Nov/2023:20:07:30 +0400", OffsetDateTime.of(
                2023, 11, 19, 20, 7, 30, 0, ZoneOffset.of("+04:00")
            )),
            Arguments.of("wrong format", null)
        };
    }

    @ParameterizedTest
    @MethodSource("times")
    public void timeParseTest(String time, OffsetDateTime expected) {
        OffsetDateTime result = TimeParsersChain.parse(time);

        assertThat(result).isEqualTo(expected);
    }

}
