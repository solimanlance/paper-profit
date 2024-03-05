package persistence;

import model.Trader;
import model.Stock;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Inspired by Paul Carter's JsonSerializationDemo, https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 * Reads a trader from JSON data stored in files
 */
public class JsonReader {
    private String path;

    // EFFECTS: constructs JSON reader to read from source
    public JsonReader(String path) {
        this.path = path;
    }

    // EFFECTS: reads users trader from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Trader read() throws IOException {
        String jsonData = readFile(path);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTrader(jsonObject);
    }

    // EFFECTS: parses trader from JSON object and returns it
    public Trader parseTrader(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double funds = jsonObject.getDouble("funds");
        Trader t  = new Trader(name);
        t.setFunds(funds);
        addPortfolio(t, jsonObject);
        return t;
    }

    // MODIFIES: t
    // EFFECTS: parses stocks from JSON object and adds them to portfolio
    private void addPortfolio(Trader t, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("portfolio");
        for (Object json : jsonArray) {
            JSONObject nextStock = (JSONObject) json;
            addStock(t, nextStock);
        }
    }

    // MODIFIES: t
    // EFFECTS: parses stock from JSON portfolio and adds it to portfolio
    private void addStock(Trader t, JSONObject jsonObject) {
        String symbol = jsonObject.getString("symbol");
        double price = jsonObject.getDouble("price");
        int amount = jsonObject.getInt("amount");
        Stock stock = new Stock(symbol, price);
        stock.setAmount(amount);
        t.addStock(stock);
    }



    // EFFECTS: reads source file as string and returns it
    private String readFile(String path) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

}
