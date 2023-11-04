package edu.project2;

import edu.project2.Generation.Generator;
import edu.project2.Generation.RecursiveBacktracker;
import edu.project2.Generation.SidewinderGenerator;
import edu.project2.Maze.Cell;
import edu.project2.Maze.Maze;
import edu.project2.Solution.BFSSolver;
import edu.project2.Solution.Solver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GeneratorTest {
    @Test
    @DisplayName("Тест существования пути при генерации recursive backtracker")
    public void recursiveBacktrackerTest() {
        Generator generator = new RecursiveBacktracker();

        Maze maze = generator.generate(11, 11);
        Solver solver = new BFSSolver();
        List<Cell> path = solver.solve(maze, maze.getCell(0, 1), maze.getCell(10, 9));

        assertThat(path).isNotNull();
    }

    @Test
    @DisplayName("Тест существования пути при генерации sidewinder")
    public void sidewinderTest() {
        Generator generator = new SidewinderGenerator();

        Maze maze = generator.generate(11, 11);
        Solver solver = new BFSSolver();
        List<Cell> path = solver.solve(maze, maze.getCell(0, 1), maze.getCell(10, 9));

        assertThat(path).isNotNull();
    }
}
