package model;

import org.json.JSONObject;
import persistence.Writable;

public class Stock implements Writable {

    // Represents a stock with a symbol, price, and amount of shares, with amount 0
    private String symbol;
    private double price;
    private int amount;

    // REQUIRES: symbol is not blank, price > 0, amount > 0
    // EFFECTS: constructs stock with given symbol and price
    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
        this.amount = 0;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    // gets stock symbol with how much the user currently has of it
    public String getRecord() {
        return symbol + "," + amount * price;
    }

    // REQUIRES: amountBought > 0
    // EFFECTS: adds amount to stock in user's portfolio
    public void addStock(int amountBought) {
        amount = amount + amountBought;
    }

    // REQUIRES: amountSold > 0
    // EFFECTS: subtracts amount to stock in user's portfolio
    public void subtractStock(int amountSold) {
        amount = amount - amountSold;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("symbol", symbol);
        json.put("price", price);
        json.put("amount", amount);
        return json;
    }

}
