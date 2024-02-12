package model;

import java.util.ArrayList;

public class Portfolio {

    ArrayList<Stock> portfolio;

    public Portfolio() {
        this.portfolio = new ArrayList<Stock>();
    }

    public ArrayList<Stock> getPortfolio() {
        return portfolio;
    }

    // for testing purposes
    public int getSize() {
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


    // REQUIRES: sellAmount <= s.getAmount()
    // MODIFIES: this
    // EFFECTS: subtracts amount of stock to portfolio, if all amount is subtracted removes it
    // from portfolio.
    public void subtractStock(Stock stock, int sellAmount) {
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
