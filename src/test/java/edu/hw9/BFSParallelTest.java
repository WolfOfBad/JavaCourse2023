package edu.hw9;

import edu.project2.Generation.RecursiveBacktracker;
import edu.project2.Maze.Maze;
import edu.project2.Solution.BFSSolver;
import edu.project2.Solution.Solver;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BFSParallelTest {
    @Test
    public void test() {
        Maze maze = new RecursiveBacktracker().generate(15, 15);

        Solver singleThread = new BFSSolver();
        Solver parallel = new BFSSolverParallel();

        var resultSingleThread = singleThread.solve(
            maze,
            maze.getCell(0, 1),
            maze.getCell(maze.getHeight() - 1, maze.getWidth() - 2)
        );
        var resultParallel = parallel.solve(
            maze,
            maze.getCell(0, 1),
            maze.getCell(maze.getHeight() - 1, maze.getWidth() - 2)
        );

        assertThat(resultSingleThread).asList().isEqualTo(resultParallel);
    }

}
