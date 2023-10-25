package edu.hw3.StockMarket;

import java.util.Comparator;

public class StockPriceComparator implements Comparator<Stock> {
    @Override
    public int compare(Stock o1, Stock o2) {
        return Integer.compare(o1.getCost(), o2.getCost());
    }
}
