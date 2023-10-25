package edu.hw3;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BracketsClusterizerTest {

    private static Arguments[] correctStrings() {
        return new Arguments[] {
            Arguments.of("()()", List.of("()", "()")),
            Arguments.of("(())", List.of("(())")),
            Arguments.of("(word)", List.of("(word)"))
        };
    }

    @ParameterizedTest
    @MethodSource("correctStrings")
    @DisplayName("Тест корректных строк")
    public void correctStringsTest(String string, List<String> expected) {
        BracketsClusterizer obj = new BracketsClusterizer();
        List<String> result = obj.clusterize(string);

        assertThat(result).asList().isEqualTo(expected);
    }

    private static Arguments[] incorrectStrings() {
        return new Arguments[] {
            Arguments.of("(()"),
            Arguments.of("())"),
            Arguments.of("word"),
            Arguments.of(")("),
        };
    }

    @ParameterizedTest
    @MethodSource("incorrectStrings")
    @DisplayName("Тест некорректных строк")
    public void incorrectStringsTest(String string) {
        BracketsClusterizer obj = new BracketsClusterizer();
        List<String> result = obj.clusterize(string);

        assertThat(result).isNull();
    }

}
