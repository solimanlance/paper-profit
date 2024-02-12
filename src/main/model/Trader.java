package model;

import java.util.ArrayList;

// Represents a stock trader having an id, name, portfolio of stock, and funds in dollars.
public class Trader {
    private static int nextAccountId = 0;
    private int id;
    private String name;
    private ArrayList<Stock> portfolio;
    private double funds;
    private static final int initialFunds = 5000;

    // REQUIRES: name is not blank
    // EFFECTS: constructs a new trader with their name, an empty list of stocks,
    // a unique id, and initial funds (set to 5000 dollars)
    public Trader(String name, ArrayList<Stock> portfolio) {
        this.name = name;
        this.portfolio = portfolio;
        this.id = nextAccountId++;
        this.funds = initialFunds;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Stock> getPortfolio() {
        return portfolio;
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

    // for testing purposes
    public int getPortfolioSize() {
        return portfolio.size();
    }

    // returns total portfolio value for testing
    public double getPortfolioValue() {
        double total = 0;
        for (Stock s : portfolio) {
            total += s.getAmount() * s.getPrice();
        }
        return total;
    }


    // REQUIRES: funds >= stock price * buyAmount
    // MODIFIES: this
    // EFFECTS: stock is bought and updated in portfolio, user funds are updated
    public void buyStock(Stock stock, int buyAmount) {
        double price = stock.getPrice() * buyAmount;
        funds -= price;
        boolean stockInPortfolio = false;
        for (Stock s : portfolio) {
            if (stock.getSymbol().equals(s.getSymbol())) {
                s.addStock(buyAmount);
                stockInPortfolio = true;
                break;
            }
        }
        if (!stockInPortfolio) {
            stock.setAmount(buyAmount);
            portfolio.add(stock);
        }
    }

    // REQUIRES: sellAmount <= s.getAmount()
    // MODIFIES: this
    // EFFECTS: stock is sold and updated in portfolio, user funds are updated
    public void sellStock(Stock stock, int sellAmount) {
        double price = stock.getPrice() * sellAmount;
        funds += price;
        for (Stock s : portfolio) {
            if (s.getSymbol().equals(stock.getSymbol())) {
                stock.subtractStock(sellAmount);
                if (s.getAmount() - sellAmount == 0) {
                    portfolio.remove(stock);
                }
            }
        }
    }
}
