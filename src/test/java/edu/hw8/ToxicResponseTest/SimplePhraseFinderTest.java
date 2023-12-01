package edu.hw8.ToxicResponseTest;

import edu.hw8.ToxicResponseServer.Server.PhraseFinder.PhraseFinder;
import edu.hw8.ToxicResponseServer.Server.PhraseFinder.SimplePhraseFinder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class SimplePhraseFinderTest {
    private static Arguments[] args() {
        return new Arguments[] {
            Arguments.of("интеллект", "Чем ниже интеллект, тем громче оскорбления"),
            Arguments.of("тест", "Невозможно найти фразу")
        };
    }

    @ParameterizedTest
    @MethodSource("args")
    public void finderTest(String word, String expected) {
        PhraseFinder finder = new SimplePhraseFinder();

        String result = finder.find(word);

        assertThat(result).isEqualTo(expected);
    }
}
