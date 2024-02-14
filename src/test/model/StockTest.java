package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class StockTest {

    Stock stock1 = new Stock("ABCD", 50);
    Stock stock2 = new Stock("EFGH", 100);

    @BeforeEach
    void runBefore() {
        stock2.setAmount(5);
    }

    @Test
    void testConstructor() {
        assertEquals(stock1.getAmount(), 0);
        assertEquals(stock1.getPrice(), 50);
        assertEquals(stock1.getSymbol(), "ABCD");
    }

    @Test
    void testAddStock() {
        stock1.addStock(1);
        assertEquals(stock1.getRecord(), "ABCD,50.0");
        assertEquals(stock1.getAmount(), 1);
        stock1.addStock(7);
        assertEquals(stock1.getAmount(), 8);
    }

    @Test
    void testSubtractStock() {
        stock2.subtractStock(2);
        assertEquals(stock2.getAmount(), 3);
        stock2.subtractStock(3);
        assertEquals(stock2.getAmount(), 0);
    }
}
