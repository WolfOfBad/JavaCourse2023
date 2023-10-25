package edu.hw3;

import edu.hw3.StockMarket.SimpleStockMarket;
import edu.hw3.StockMarket.Stock;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StockMarketTest {

    @Test
    public void simpleStockMarketTest() {
        Stock s1 = new Stock("a", 2);
        Stock s2 = new Stock("a", 1);
        Stock s3 = new Stock("a", 3);

        SimpleStockMarket obj = new SimpleStockMarket();
        obj.add(s1);
        obj.add(s2);
        obj.add(s3);

        assertThat(obj.mostValuableStock().getCost()).isEqualTo(3);
        obj.remove(obj.mostValuableStock());

        assertThat(obj.mostValuableStock().getCost()).isEqualTo(2);
        obj.remove(obj.mostValuableStock());

        assertThat(obj.mostValuableStock().getCost()).isEqualTo(1);
    }

}
