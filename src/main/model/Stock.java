package model;

public class Stock {

    // Represents a stock with a symbol, price, and amount of shares
    private String name;
    private String symbol;
    private double price;
    private int amount;

    // REQUIRES: symbol is not blank, price > 0, amount > 0
    // EFFECTS: constructs stock with given symbol and price
    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
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

    // EFFECTS: adds amount to stock in user's portfolio
    public void addStock(int amountBought) {
        amount = amount + amountBought;
    }

    // EFFECTS: subtracts amount to stock in user's portfolio
    public void subtractStock(int amountBought) {
        amount = amount - amountBought;
    }
}
