package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class PortfolioTest {

    Stock stock1 = new Stock("GOOG", 150);
    Stock stock2 = new Stock("AAAA", 20);
    Stock stock3 = new Stock("GME", 10);
    Portfolio portfolio1 = new Portfolio();
    Portfolio portfolio2 = new Portfolio();
    Trader trader1 = new Trader("John");
    Trader trader2 = new Trader("Lance");

    @BeforeEach
    void runBefore() {
        trader1.setPortfolio(portfolio1);
        trader2.setPortfolio(portfolio2);
        trader2.buyStock(stock2, 5);
        // reset next account id's to avoid getting wrong id's in tests
        trader1.resetNextAccountId();
        trader2.resetNextAccountId();
    }

    @Test
    void testConstructor() {
        assertEquals(portfolio1.getSize(), 0);
    }

    @Test
    void testAddStock() {
        trader1.buyStock(stock1,2);
        assertEquals(portfolio1.getSize(), 1);
        assertEquals(portfolio1.getValue(), 300);

        trader1.buyStock(stock2, 4);
        trader1.buyStock(stock3,2);
        assertEquals(portfolio1.getSize(), 3);
        assertEquals(portfolio1.getValue(), 400);
    }

    @Test
    void testAddStockOther() {
        portfolio1.addStock(stock1);
        assertEquals(portfolio1.getSize(), 1);
        assertEquals(portfolio1.get(0), stock1);
    }

    @Test
    void testAddOwnedStock() {
        assertEquals(portfolio2.getSize(), 1);
        trader2.buyStock(stock2,2);
        assertEquals(portfolio2.getValue(), 140);

        trader2.buyStock(stock2,2);
        assertEquals(portfolio2.getSize(), 1);
        assertEquals(portfolio2.getValue(), 180);

    }

   @Test
    void testSubtractStock() {
        trader2.sellStock(stock2, 2);
        assertEquals(portfolio2.getSize(), 1);
        assertEquals(portfolio2.getValue(), 60);

        trader2.sellStock(stock2, 3);
        assertEquals(portfolio2.getSize(), 0);
        assertEquals(portfolio2.getValue(), 0);

        trader2.buyStock(stock2, 1);
        trader2.buyStock(stock3, 2);
        trader2.buyStock(stock1, 2);

        trader2.sellStock(stock3, 2);
        assertEquals(portfolio2.getSize(), 2);

        trader2.sellStock(stock1, 2);
       assertEquals(portfolio2.getSize(), 1);
    }

    @Test
    void testGetStock() {
        trader2.buyStock(stock2,2);
        assertEquals(portfolio2.getStock("AAAA"), stock2);
        assertNull(portfolio2.getStock("qfef"));
    }

    @Test
    void testGetPortfolio() {
        ArrayList<Stock> portfolioCopy = new ArrayList<>();
        assertEquals(portfolio1.getPortfolio(), portfolioCopy);
        portfolioCopy.add(stock1);
        trader1.buyStock(stock1,2);
        assertEquals(portfolio1.getPortfolio(), portfolioCopy);

    }

}
