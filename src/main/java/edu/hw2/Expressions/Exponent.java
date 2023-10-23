package edu.hw2.Expressions;

public record Exponent(Expr value, Expr power) implements Expr {
    public Exponent(Expr value, double power) {
        this(value, new Constant(power));
    }

    public Exponent(double value, Expr power) {
        this(new Constant(value), power);
    }

    public Exponent(double value, double power) {
        this(new Constant(value), new Constant(power));
    }

    @Override
    public double evaluate() {
        // if value is negative and power not an integer returns NaN
        return Math.pow(value.evaluate(), power.evaluate());
    }
}
