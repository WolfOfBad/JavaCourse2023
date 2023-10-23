package edu.hw2.Expressions;

public record Addition(Expr first, Expr second) implements Expr {
    public Addition(Expr value, double power) {
        this(value, new Constant(power));
    }

    public Addition(double value, Expr power) {
        this(new Constant(value), power);
    }

    public Addition(double value, double power) {
        this(new Constant(value), new Constant(power));
    }

    @Override
    public double evaluate() {
        return first.evaluate() + second.evaluate();
    }
}
