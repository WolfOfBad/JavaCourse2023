package edu.project2.Renderer;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Maze;
import java.util.List;

public interface Renderer {
    void render(Maze maze);

    void render(Maze maze, List<Cell> path);
}
