package model;

import javax.sound.sampled.Port;
import java.util.ArrayList;

// Represents a stock trader having an id, name, portfolio of stock, and funds in dollars.
public class Trader {
    private static int nextAccountId = 0;
    private int id;
    private String name;
    private Portfolio portfolio;
    private double funds;
    private static final int initialFunds = 5000;

    // REQUIRES: name is not blank
    // EFFECTS: constructs a new trader with their name, an empty list of stocks,
    // a unique id, and initial funds (set to 5000 dollars)
    public Trader(String name, Portfolio portfolio) {
        this.name = name;
        this.portfolio = portfolio;
        this.id = nextAccountId++;
        this.funds = initialFunds;
    }

    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }

    public double getFunds() {
        return funds;
    }

    // resets nextAccountId for testing purposes
    public void resetNextAccountId() {
        nextAccountId = 0;
    }

    // returns total portfolio value for testing
    public double getPortfolioValue() {
        return portfolio.getValue();
    }

    // for testing purposes
    public int getPortfolioSize() {
        return portfolio.getSize();
    }


    // REQUIRES: funds >= stock price * buyAmount
    // MODIFIES: this
    // EFFECTS: stock is bought and updated in portfolio, user funds are updated
    public void buyStock(Stock stock, int buyAmount) {
        double price = stock.getPrice() * buyAmount;
        funds -= price;
        portfolio.addStock(stock, buyAmount);
    }

    // REQUIRES: sellAmount <= s.getAmount()
    // MODIFIES: this
    // EFFECTS: stock is sold and updated in portfolio, user funds are updated
    public void sellStock(Stock stock, int sellAmount) {
        double price = stock.getPrice() * sellAmount;
        funds += price;
        portfolio.subtractStock(stock, sellAmount);
    }

    // REQUIRES: amount > 0, amount <= 5000
    // MODIFIES: this
    // EFFECTS: adds amount of funds to trader's funds on hand
    public void addFunds(double amount) {
        funds += amount;
    }
}
