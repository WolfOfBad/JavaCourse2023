package edu.project2;

import edu.project2.Generation.Generator;
import edu.project2.Generation.RecursiveBacktracker;
import edu.project2.Maze.Cell;
import edu.project2.Maze.Maze;
import edu.project2.Renderer.ConsoleRenderer;
import edu.project2.Renderer.Renderer;
import edu.project2.Solution.BFSSolver;
import edu.project2.Solution.Solver;
import java.util.List;

public final class Main {
    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        Generator generator = new RecursiveBacktracker();
        // Generator generator = new SidewinderGenerator();

        Solver solver = new BFSSolver();
        // Solver solver = new DFSSolver();

        Maze maze = generator.generate(41, 41);
        List<Cell> path = solver.solve(
            maze,
            maze.getCell(0, 1),
            maze.getCell(maze.getHeight() - 1, maze.getWidth() - 2)
        );

        Renderer renderer = new ConsoleRenderer();
        renderer.render(maze, path);
    }
}
