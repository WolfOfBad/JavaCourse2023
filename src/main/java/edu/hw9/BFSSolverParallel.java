package edu.hw9;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Maze;
import edu.project2.Solution.Solver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class BFSSolverParallel implements Solver {
    private Map<Cell, Cell> backtrack;
    private ForkJoinPool pool = new ForkJoinPool();

    @Override
    public List<Cell> solve(Maze maze, Cell start, Cell end) {
        backtrack = Collections.synchronizedMap(new HashMap<>());
        backtrack.put(start, null);

        pool.invoke(new CellProcessor(maze, start));

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

    private class CellProcessor extends RecursiveAction {
        private Maze maze;
        private Cell cell;
        private static final int[][] DIRECTIONS = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        CellProcessor(Maze maze, Cell cell) {
            this.maze = maze;
            this.cell = cell;
        }

        @Override
        protected void compute() {
            List<CellProcessor> processing = new ArrayList<>();
            for (int[] direction : DIRECTIONS) {
                int y = cell.getY() + direction[0];
                int x = cell.getX() + direction[1];

                if (0 <= x && x < maze.getWidth()
                    && 0 <= y && y < maze.getHeight()
                    && maze.getCell(y, x).getType() == Cell.Type.PASSAGE
                    && !backtrack.containsKey(maze.getCell(y, x))) {
                    backtrack.put(maze.getCell(y, x), cell);
                    processing.add(new CellProcessor(maze, maze.getCell(y, x)));
                    processing.getLast().fork();
                }
            }
            for (CellProcessor processor : processing) {
                processor.join();
            }
        }
    }

}
