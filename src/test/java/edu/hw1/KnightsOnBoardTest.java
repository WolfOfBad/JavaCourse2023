package edu.hw1;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import static edu.hw1.KnightsOnBoard.KnightsOnBoard.knightBoardCapture;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KnightsOnBoardTest {
    @ParameterizedTest(name = "#{index} -> Board = {0}, Expected result = {1}")
    @ArgumentsSource(CorrectBoardArgumentsProvider.class)
    @DisplayName("Тест проверки правильного формата доски")
    public void knightOnBoardShouldWorkDifferentArguments(int[][] board, boolean expectedResult) {
        boolean result = knightBoardCapture(board);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static class CorrectBoardArgumentsProvider implements ArgumentsProvider {
        private final int[][][] correctBoards = {
            {
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 0}
            },
            {
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1}
            },
            {
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}
            }
        };

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                Arguments.of(correctBoards[0], true),
                Arguments.of(correctBoards[1], false),
                Arguments.of(correctBoards[2], false)
            );
        }
    }

    @ParameterizedTest(name = "#{index} -> Incorrect board = {0}")
    @ArgumentsSource(IncorrectBoardArgumentsProvider.class)
    @DisplayName("Тест неправильного формата доски")
    public void knightOnBoardWrongFormatTest(int[][] board, Exception expectedException) {
        Exception thrown = assertThrows(Exception.class, () -> knightBoardCapture(board));

        assertThat(thrown).hasMessage(expectedException.getMessage());
    }

    private static class IncorrectBoardArgumentsProvider implements ArgumentsProvider {
        private final int[][][] incorrectBoards = {
            {
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
            },
            {
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            {
                {0, 0, 0, 0, 1, 0, 0, 0},
                null,
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
            },
            null
        };

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                Arguments.of(incorrectBoards[0], new Exception("Wrong board format")),
                Arguments.of(incorrectBoards[1], new Exception("Wrong board format")),
                Arguments.of(incorrectBoards[2], new Exception("Board has null row")),
                Arguments.of(incorrectBoards[3], new Exception("Board is null"))
            );
        }
    }
}
