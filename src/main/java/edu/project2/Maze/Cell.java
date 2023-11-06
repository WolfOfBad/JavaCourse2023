package edu.project2.Maze;

public class Cell {
    private final int y;
    private final int x;
    private Type type;

    public Cell(int y, int x, Type type) {
        this.y = y;
        this.x = x;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        WALL,
        PASSAGE
    }
}
