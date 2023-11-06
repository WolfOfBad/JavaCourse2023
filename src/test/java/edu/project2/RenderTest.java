package edu.project2;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Maze;
import edu.project2.Renderer.ConsoleColors;
import edu.project2.Renderer.ConsoleRenderer;
import edu.project2.Renderer.Renderer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RenderTest {
    @Test
    @DisplayName("Тест отрисовки в консоль нерешенного лабиринта")
    public void unsolvedMazeRenderTest() {
        Cell[][] grid = {
            {
                new Cell(0, 0, Cell.Type.PASSAGE),
                new Cell(0, 1, Cell.Type.WALL),
            }
        };
        Maze maze = new Maze(grid);

        LoggerStringsGetter loggerStringsGetter = new LoggerStringsGetter(ConsoleRenderer.class);
        Renderer renderer = new ConsoleRenderer();
        renderer.render(maze);

        List<String> strings = loggerStringsGetter.getStrings();
        loggerStringsGetter.endReading();

        assertThat(strings.get(1)).isEqualTo("   "
            + ConsoleColors.WHITE_BACKGROUND + "   " + ConsoleColors.RESET);
    }

    @Test
    @DisplayName("Тест отрисовки в консоль решенного лабиринта")
    public void solvedMazeRenderTest() {
        Cell[][] grid = {
            {
                new Cell(0, 0, Cell.Type.PASSAGE),
                new Cell(0, 1, Cell.Type.WALL),
                new Cell(0, 2, Cell.Type.PASSAGE)
            }
        };
        Maze maze = new Maze(grid);
        List<Cell> path = List.of(maze.getCell(0, 2));

        LoggerStringsGetter loggerStringsGetter = new LoggerStringsGetter(ConsoleRenderer.class);
        Renderer renderer = new ConsoleRenderer();
        renderer.render(maze, path);

        List<String> strings = loggerStringsGetter.getStrings();
        loggerStringsGetter.endReading();

        assertThat(strings.get(1)).isEqualTo("   "
            + ConsoleColors.WHITE_BACKGROUND + "   " + ConsoleColors.RESET
            + ConsoleColors.RED + " ● " + ConsoleColors.RESET);
    }
}
