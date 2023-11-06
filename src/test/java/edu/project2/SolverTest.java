package edu.project2;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Maze;
import edu.project2.Solution.BFSSolver;
import edu.project2.Solution.DFSSolver;
import edu.project2.Solution.Solver;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SolverTest {
    private final Cell[][] correctGrid = new Cell[][] {
        {
            new Cell(0, 0, Cell.Type.PASSAGE),
            new Cell(0, 1, Cell.Type.WALL),
            new Cell(0, 2, Cell.Type.WALL)
        },
        {
            new Cell(1, 0, Cell.Type.PASSAGE),
            new Cell(1, 1, Cell.Type.PASSAGE),
            new Cell(1, 2, Cell.Type.PASSAGE)
        },
        {
            new Cell(2, 0, Cell.Type.WALL),
            new Cell(2, 1, Cell.Type.WALL),
            new Cell(2, 2, Cell.Type.PASSAGE)
        }
    };
    private final Cell[][] incorrectGrid = new Cell[][] {
        {
            new Cell(0, 0, Cell.Type.PASSAGE),
            new Cell(0, 1, Cell.Type.WALL),
            new Cell(0, 2, Cell.Type.WALL)
        },
        {
            new Cell(1, 0, Cell.Type.PASSAGE),
            new Cell(1, 1, Cell.Type.WALL),
            new Cell(1, 2, Cell.Type.PASSAGE)
        },
        {
            new Cell(2, 0, Cell.Type.WALL),
            new Cell(2, 1, Cell.Type.WALL),
            new Cell(2, 2, Cell.Type.PASSAGE)
        }
    };

    @Test
    @DisplayName("Тест обхода лабиринта в ширину")
    public void BFSTest() {
        Maze maze = new Maze(correctGrid);

        Solver solver = new BFSSolver();
        List<Cell> path = solver.solve(maze, maze.getCell(0, 0), maze.getCell(2, 2));

        assertThat(path.size()).isEqualTo(5);
        assertThat(path.get(0)).isEqualTo(maze.getCell(0, 0));
        assertThat(path.get(1)).isEqualTo(maze.getCell(1, 0));
        assertThat(path.get(2)).isEqualTo(maze.getCell(1, 1));
        assertThat(path.get(3)).isEqualTo(maze.getCell(1, 2));
        assertThat(path.get(4)).isEqualTo(maze.getCell(2, 2));
    }

    @Test
    @DisplayName("Тест обхода лабиринта в ширину. Не существует пути")
    public void BFSTestNull() {
        Maze maze = new Maze(incorrectGrid);

        Solver solver = new BFSSolver();
        List<Cell> path = solver.solve(maze, maze.getCell(0, 0), maze.getCell(2, 2));

        assertThat(path).isNull();
    }

    @Test
    @DisplayName("Тест обхода лабиринта в глубину")
    public void DFSTest() {
        Maze maze = new Maze(correctGrid);

        Solver solver = new DFSSolver();
        List<Cell> path = solver.solve(maze, maze.getCell(0, 0), maze.getCell(2, 2));

        assertThat(path.size()).isEqualTo(5);
        assertThat(path.get(0)).isEqualTo(maze.getCell(0, 0));
        assertThat(path.get(1)).isEqualTo(maze.getCell(1, 0));
        assertThat(path.get(2)).isEqualTo(maze.getCell(1, 1));
        assertThat(path.get(3)).isEqualTo(maze.getCell(1, 2));
        assertThat(path.get(4)).isEqualTo(maze.getCell(2, 2));
    }

    @Test
    @DisplayName("Тест обхода лабиринта в глубину. Не существует пути")
    public void DFSTestNull() {
        Maze maze = new Maze(incorrectGrid);

        Solver solver = new DFSSolver();
        List<Cell> path = solver.solve(maze, maze.getCell(0, 0), maze.getCell(2, 2));

        assertThat(path).isNull();
    }
}
