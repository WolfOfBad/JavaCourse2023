package edu.hw1;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import static edu.hw1.NestedArray.isNestable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NestedArrayTest {
    @ParameterizedTest(name = "#{index} -> First array = {0}, Second array = {1}, Expected result = {2}")
    @ArgumentsSource(correctDataArgumentsProvider.class)
    @DisplayName("Тест вложенных массивов на разных аргументах")
    public void isNestableShouldWorkDifferentArguments(int[] first, int[] second, boolean expectedResult) {
        boolean result = isNestable(first, second);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static class correctDataArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4}, new int[] {0, 6}, true),
                Arguments.of(new int[] {3, 1}, new int[] {4, 0}, true),
                Arguments.of(new int[] {9, 9, 8}, new int[] {8, 9}, false),
                Arguments.of(new int[] {1, 2, 3, 4}, new int[] {2, 3}, false),
                Arguments.of(new int[] {-4, 5, 18, 4}, new int[] {-5, 3, -3, 2, 15, 4, 2, 15, 19}, true)
            );
        }
    }

    @Test
    @DisplayName("Тест на значение null первого массива")
    public void nullTest1() {
        // given
        int[] first = null;
        int[] second = {-5, 3, -3};

        // when
        Exception thrown = assertThrows(Exception.class, () -> isNestable(first, second));

        // then
        assertThat(thrown).hasMessage("First array is null");
    }

    @Test
    @DisplayName("Тест на значение null второго массива")
    public void nullTest2() {
        // given
        int[] first = {9, 9, 8};
        int[] second = null;

        // when
        Exception thrown = assertThrows(Exception.class, () -> isNestable(first, second));

        // then
        assertThat(thrown).hasMessage("Second array is null");
    }

}
