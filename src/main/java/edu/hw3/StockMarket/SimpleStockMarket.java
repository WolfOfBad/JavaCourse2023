package edu.hw3.StockMarket;

import java.util.PriorityQueue;
import org.jetbrains.annotations.NotNull;

public class SimpleStockMarket implements StockMarket {
    private final PriorityQueue<Stock> stocks = new PriorityQueue<>(new StockPriceComparator().reversed());

    @Override
    public void add(@NotNull Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(@NotNull Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
