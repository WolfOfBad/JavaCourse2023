package edu.hw2.Expressions;

public sealed interface Expr permits Addition, Constant, Exponent, Multiplication, Negate {
    double evaluate();
}
