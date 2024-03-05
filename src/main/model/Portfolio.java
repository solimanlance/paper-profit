package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a stock traders portfolio
public class Portfolio {

    ArrayList<Stock> portfolio;

    // EFFECTS: constructs an empty arraylist as a portfolio for the trader
    public Portfolio() {
        this.portfolio = new ArrayList<Stock>();
    }

    // EFFECTS: returns portfolio as an arraylist
    public ArrayList<Stock> getPortfolio() {
        return portfolio;
    }

    // for testing purposes
    public int getSize() {
        return portfolio.size();
    }

    // returns total portfolio value for testing
    public double getValue() {
        double total = 0;
        if (this.getSize() == 0) {
            return 0;
        }
        for (Stock s : portfolio) {
            total += s.getAmount() * s.getPrice();
        }
        return total;
    }

    // EFFECTS: returns stock with given symbol, if not in portfolio returns null
    public Stock getStock(String symbol) {
        for (Stock s : portfolio) {
            if (s.getSymbol().equals(symbol)) {
                return s;
            }
        }
        return null;
    }

    // REQUIRES: funds >= stock price * buyAmount
    // MODIFIES: this
    // EFFECTS: adds amount of stock to portfolio, adds stock to list if not already there
    public void addStock(Stock stock, int buyAmount) {
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

    // MODIFIES: this
    // EFFECTS: adds amount of stock to portfolio after being read from json
    public void addStock(Stock stock) {
        portfolio.add(stock);
    }


    // REQUIRES: sellAmount <= s.getAmount()
    // MODIFIES: this
    // EFFECTS: subtracts amount of stock to portfolio, if all amount is subtracted removes it
    // from portfolio.
    public void subtractStock(Stock stock, int sellAmount) {
        for (Stock s : portfolio) {
            if (s.getSymbol().equals(stock.getSymbol())) {
                stock.subtractStock(sellAmount);
                if (s.getAmount() == 0) {
                    portfolio.remove(stock);
                    break;
                }
            }
        }
    }


}
