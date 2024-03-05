package persistence;

import model.Portfolio;
import model.Stock;
import model.Trader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReadTrader() {
        JsonReader reader = new JsonReader("./data/testReaderTrader.json");
        try {
            Trader t = reader.read();
            ArrayList<Stock> stocks = t.getPortfolioStocks();
            Portfolio p = t.getPortfolio();
            Stock a = p.get(0);
            Stock n = p.get(1);
            assertEquals("user", t.getName());
            assertEquals(10, t.getFunds());
            assertEquals(2, stocks.size());

            assertEquals("AMZN", a.getSymbol());
            assertEquals(2, a.getAmount());
            assertEquals(150, a.getPrice());

            assertEquals("NVDA", n.getSymbol());
            assertEquals(1, n.getAmount());
            assertEquals(700, n.getPrice());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
