package edu.hw3.StockMarket;

public class Stock {
    private final String company;
    // other parameters
    private int cost;

    public Stock(String company, int cost) {
        this.company = company;
        this.cost = cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
