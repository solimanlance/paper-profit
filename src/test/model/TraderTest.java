package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TraderTest {

    Stock stock1 = new Stock("GOOG", 150);
    Stock stock2 = new Stock("AAAA", 20);
    Stock stock3 = new Stock("GME", 10);
    Trader trader1 = new Trader("John", new Portfolio());
    Trader trader2 = new Trader("Lance", new Portfolio());

    @BeforeEach
    void runBefore() {
        trader2.buyStock(stock2, 5);
        // reset next account id's to avoid getting wrong id's in tests
        trader1.resetNextAccountId();
        trader2.resetNextAccountId();
    }

    @Test
    void testConstructor() {
       assertEquals(trader1.getPortfolioSize(), 0);
       assertEquals(trader1.getName(), "John");
       assertEquals(trader1.getFunds(), 5000);
       assertEquals(trader2.getId(), 1);
       assertEquals(trader1.getId(), 0);
    }

    @Test
    void testBuyStock() {
        assertEquals(trader1.getPortfolioSize(), 0);
        trader1.buyStock(stock1,2);
        assertEquals(trader1.getPortfolioSize(), 1);
        assertEquals(trader1.getFunds(), 4700);
        assertEquals(trader1.getPortfolioValue(), 300);

        trader1.buyStock(stock2, 4);
        trader1.buyStock(stock3,2);
        assertEquals(trader1.getPortfolioSize(), 3);
        assertEquals(trader1.getFunds(), 4600);
        assertEquals(trader1.getPortfolioValue(), 400);
    }

    @Test
    void testBuyOwnedStock() {
        assertEquals(trader2.getPortfolioSize(), 1);
        assertEquals(trader2.getFunds(), 4900);
        trader2.buyStock(stock2,2);
        assertEquals(trader2.getPortfolioValue(), 140);
        assertEquals(trader2.getFunds(), 4860);

        trader2.buyStock(stock2,2);
        assertEquals(trader2.getPortfolioSize(), 1);
        assertEquals(trader2.getFunds(), 4820);
        assertEquals(trader2.getPortfolioValue(), 180);

    }

    @Test
    void testSellStock() {
        trader2.sellStock(stock2, 2);
        assertEquals(trader2.getFunds(), 4940);
        assertEquals(trader2.getPortfolioValue(), 60);

        trader2.sellStock(stock2, 3);
        assertEquals(trader2.getFunds(), 5000);
        assertEquals(trader2.getPortfolioValue(), 0);
    }

    @Test
    void testAddFunds() {
        trader1.addFunds(4999);
        assertEquals(trader1.getFunds(), 9999);
        trader1.addFunds(1);
        assertEquals(trader1.getFunds(), 10000);
    }

}
