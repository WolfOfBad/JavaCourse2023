package edu.hw2.Expressions;

public record Multiplication(Expr first, Expr second) implements Expr {
    public Multiplication(Expr value, double power) {
        this(value, new Constant(power));
    }

    public Multiplication(double value, Expr power) {
        this(new Constant(value), power);
    }

    public Multiplication(double value, double power) {
        this(new Constant(value), new Constant(power));
    }

    @Override
    public double evaluate() {
        return first.evaluate() * second.evaluate();
    }
}
