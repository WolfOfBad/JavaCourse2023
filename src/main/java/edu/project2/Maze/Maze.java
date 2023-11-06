package edu.project2.Maze;

import org.jetbrains.annotations.NotNull;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(@NotNull Cell[][] grid) {
        if (grid.length == 0) {
            throw new IllegalArgumentException("The grid is empty");
        }

        this.grid = grid;

        this.height = grid.length;
        this.width = grid[0].length;
        for (Cell[] row : grid) {
            if (row.length != grid[0].length) {
                throw new IllegalArgumentException("Rows have different lengths");
            }
        }
    }

    public Cell getCell(int y, int x) {
        return grid[y][x];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
