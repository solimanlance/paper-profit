package persistence;

import model.Portfolio;
import model.Stock;
import model.Trader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterTrader() {
        try {
            Trader t = new Trader("writer");
            Portfolio p = t.getPortfolio();
            Stock s = new Stock("AMZN", 150);
            Stock n = new Stock("NVDA", 700);
            p.addStock(s, 3);
            p.addStock(n, 2);
            JsonWriter writer = new JsonWriter("./data/testWriterTrader.json");

            writer.open();
            writer.write(t);
            writer.close();

            // same code from JsonReaderTest
            JsonReader reader = new JsonReader("./data/testWriterTrader.json");
            Trader t1 = reader.read();
            ArrayList<Stock> stocks = t.getPortfolioStocks();
            Portfolio p1 = t.getPortfolio();
            Stock a1 = p1.get(0);
            Stock n1 = p1.get(1);
            assertEquals("writer", t1.getName());
            assertEquals(5000, t1.getFunds());
            assertEquals(2, stocks.size());

            assertEquals("AMZN", a1.getSymbol());
            assertEquals(3, a1.getAmount());
            assertEquals(150, a1.getPrice());

            assertEquals("NVDA", n1.getSymbol());
            assertEquals(2, n1.getAmount());
            assertEquals(700, n1.getPrice());

        } catch (IOException e) {
            fail("Should not have thrown exception");
        }
    }
}
