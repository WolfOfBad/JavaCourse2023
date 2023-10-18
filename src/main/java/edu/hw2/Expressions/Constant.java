package edu.hw2.Expressions;

public record Constant(double value) implements Expr {
    @Override
    public double evaluate() {
        return value;
    }
}
