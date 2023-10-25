package edu.hw3;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FrequencyDictionaryTest {

    private static Arguments[] lists() {
        return new Arguments[] {
            Arguments.of(List.of("a", "bb", "a", "bb"), List.of("a", "bb"), List.of(2, 2)),
            Arguments.of(List.of(1, 2, 2, 3), List.of(1, 2, 3), List.of(1, 2, 1)),
            Arguments.of(List.of(true, false, false), List.of(true, false), List.of(1, 2))
        };
    }

    @ParameterizedTest
    @MethodSource("lists")
    public void frequencyTest(List<Object> list, List<Object> keys, List<Object> values) {
        FrequencyDictionary obj = new FrequencyDictionary();
        Map<Object, Integer> result = obj.freqDict(list);

        for (int i = 0; i < keys.size(); i++) {
            assertThat(result.get(keys.get(i))).isEqualTo(values.get(i));
        }
    }

}
