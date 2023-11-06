package edu.project2.Solution;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Maze;
import java.util.List;

public interface Solver {
    List<Cell> solve(Maze maze, Cell start, Cell end);

}
