package edu.project2.Solution;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Maze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DFSSolver implements Solver {
    private Map<Cell, Cell> backtrack;
    private static final int[][] DIRECTIONS = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    @Override
    public List<Cell> solve(Maze maze, Cell start, Cell end) {
        if (start.getType() == Cell.Type.WALL) {
            throw new IllegalArgumentException("Start cell is wall");
        }
        if (end.getType() == Cell.Type.WALL) {
            throw new IllegalArgumentException("End cell is wall");
        }

        backtrack = new HashMap<>();
        backtrack.put(start, null);
        processCell(start, maze);

        if (!backtrack.containsKey(end)) {
            return null;
        }

        List<Cell> result = new ArrayList<>();
        Cell cell = end;
        while (cell != null) {
            result.add(cell);
            cell = backtrack.get(cell);
        }

        return result.reversed();
    }

    private void processCell(Cell cell, Maze maze) {
        for (int[] direction : DIRECTIONS) {
            int y = cell.getY() + direction[0];
            int x = cell.getX() + direction[1];

            if (0 <= y && 0 <= x && y < maze.getHeight() && x < maze.getWidth()
                && maze.getCell(y, x).getType() == Cell.Type.PASSAGE
                && !backtrack.containsKey(maze.getCell(y, x))) {
                backtrack.put(maze.getCell(y, x), cell);
                processCell(maze.getCell(y, x), maze);
            }
        }
    }

}
