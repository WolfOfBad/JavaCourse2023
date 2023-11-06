package edu.project2.Generation;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SidewinderGenerator implements Generator {
    private static final Random RANDOM = new Random();
    private Maze maze;

    @Override
    public Maze generate(int height, int width) {
        if (height % 2 == 0) {
            throw new IllegalArgumentException("Height must be odd");
        }
        if (height == 1) {
            throw new IllegalArgumentException("Height must be at least 3");
        }
        if (width % 2 == 0) {

            throw new IllegalArgumentException("Width must be odd");
        }
        if (width == 1) {
            throw new IllegalArgumentException("Width must be at least 3");
        }

        maze = initMaze(height, width);

        processFirstRow();
        for (int i = 1; i < height / 2; i++) {
            processRow(i);
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

    private void processFirstRow() {
        for (int i = 2; i < maze.getWidth() - 1; i += 2) {
            maze.getCell(1, i).setType(Cell.Type.PASSAGE);
        }
    }

    private void processRow(int row) {
        int rowMaze = row * 2 + 1;
        int rowX = 1;
        List<Cell> list = new ArrayList<>(maze.getWidth());

        while (rowX < maze.getWidth() - 1) {
            Cell cell = maze.getCell(rowMaze, rowX);
            list.add(cell);
            if (RANDOM.nextInt(2) == 0) {
                Cell randomCell = list.get(RANDOM.nextInt(list.size()));
                maze.getCell(randomCell.getY() - 1, randomCell.getX()).setType(Cell.Type.PASSAGE);
                list.clear();
            } else if (rowX < maze.getWidth() - 2) {
                maze.getCell(rowMaze, rowX + 1).setType(Cell.Type.PASSAGE);
            }
            rowX += 2;
        }
        if (!list.isEmpty()) {
            Cell randomCell = list.get(RANDOM.nextInt(list.size()));
            maze.getCell(randomCell.getY() - 1, randomCell.getX()).setType(Cell.Type.PASSAGE);
        }
    }

    private void generateStartAndEnd() {
        maze.getCell(0, 1).setType(Cell.Type.PASSAGE);
        maze.getCell(maze.getHeight() - 1, maze.getWidth() - 2).setType(Cell.Type.PASSAGE);
    }
}
