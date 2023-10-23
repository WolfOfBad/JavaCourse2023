package edu.hw2.Expressions;

public record Negate(Expr value) implements Expr {
    public Negate(double value) {
        this(new Constant(value));
    }

    @Override
    public double evaluate() {
        return -1 * value.evaluate();
    }
}
