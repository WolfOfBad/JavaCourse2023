package edu.hw2.SquareAndRectangle;

public class Square extends Rectangle {
    public Square() {
        super(0, 0);
    }

    public Square(int side) {
        super(side, side);
    }

    public Square setSides(int side) {
        return new Square(side);
    }
}
