package edu.hw3;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FrequencyDictionaryTest {
    @Test
    @DisplayName("Тест со строками")
    public void stringTest() {
        List<String> list = List.of("a", "bb", "a", "bb");

        FrequencyDictionary<String> obj = new FrequencyDictionary<>();
        Map<String, Integer> result = obj.freqDict(list);

        Map<String, Integer> expected = Map.of("a", 2, "bb", 2);
        for (String key : expected.keySet()) {
            assertThat(result.get(key)).isEqualTo(expected.get(key));
            result.remove(key);
        }
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Тест с числами")
    public void integerTest() {
        List<Integer> list = List.of(1, 2, 2, 3);

        FrequencyDictionary<Integer> obj = new FrequencyDictionary<>();
        Map<Integer, Integer> result = obj.freqDict(list);

        Map<Integer, Integer> expected = Map.of(1, 1, 2, 2, 3, 1);
        for (Integer key : expected.keySet()) {
            assertThat(result.get(key)).isEqualTo(expected.get(key));
            result.remove(key);
        }
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Тест с простым классом")
    public void booleanTest() {
        class TestClass {
        }

        TestClass obj1 = new TestClass();
        TestClass obj2 = new TestClass();
        List<TestClass> list = List.of(obj1, obj1, obj2);

        FrequencyDictionary<TestClass> obj = new FrequencyDictionary<>();
        Map<TestClass, Integer> result = obj.freqDict(list);

        Map<TestClass, Integer> expected = Map.of(obj1, 2, obj2, 1);
        for (TestClass key : expected.keySet()) {
            assertThat(result.get(key)).isEqualTo(expected.get(key));
            result.remove(key);
        }
        assertThat(result.size()).isEqualTo(0);
    }
}
