package edu.project2.Generation;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class RecursiveBacktracker implements Generator {
    private static final Random RANDOM = new Random();
    private static final int[][] DIRECTIONS = {
        {-2, 0},
        {2, 0},
        {0, -2},
        {0, 2}
    };
    private Stack<Cell> stack;
    private boolean[][] visited;
    private Maze maze;

    @Override
    public Maze generate(int height, int width) {
        if (height % 2 == 0) {
            throw new IllegalArgumentException("Height must be odd");
        }
        if (width % 2 == 0) {
            throw new IllegalArgumentException("Width must be odd");
        }

        maze = initMaze(height, width);
        visited = new boolean[height / 2][width / 2];
        Cell start = startCell(height, width);

        stack = new Stack<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            Cell cell = stack.peek();
            if (!processCell(cell)) {
                stack.pop();
            }
        }

        generateStartAndEnd();
        return maze;
    }

    private Maze initMaze(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                }
            }
        }
        return new Maze(grid);
    }

    private Cell startCell(int height, int width) {
        int startRow = RANDOM.nextInt(height / 2);
        int startColumn = RANDOM.nextInt(width / 2);
        visited[startRow][startColumn] = true;

        return maze.getCell(startRow * 2 + 1, startColumn * 2 + 1);
    }

    @SuppressWarnings("MagicNumber")
    private boolean processCell(Cell cell) {
        int y = cell.getY();
        int x = cell.getX();

        List<Cell> unvisited = new ArrayList<>(4);
        for (int[] direction : DIRECTIONS) {
            int cellY = y + direction[0];
            int cellX = x + direction[1];

            if (0 < cellX && 0 < cellY
                && cellX < maze.getWidth() - 1 && cellY < maze.getHeight() - 1
                && !visited[cellY / 2][cellX / 2]) {
                unvisited.add(maze.getCell(cellY, cellX));
            }
        }

        if (!unvisited.isEmpty()) {
            Cell next = unvisited.get(RANDOM.nextInt(unvisited.size()));
            visited[next.getY() / 2][next.getX() / 2] = true;

            deleteWall(cell, next);

            stack.push(next);
            return true;
        }
        return false;
    }

    private void deleteWall(Cell first, Cell second) {
        int y = (first.getY() + second.getY()) / 2;
        int x = (first.getX() + second.getX()) / 2;

        maze.getCell(y, x).setType(Cell.Type.PASSAGE);
    }

    private void generateStartAndEnd() {
        maze.getCell(0, 1).setType(Cell.Type.PASSAGE);
        maze.getCell(maze.getHeight() - 1, maze.getWidth() - 2).setType(Cell.Type.PASSAGE);
    }

}
