package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a stock trader having an id, name, portfolio of stock, and funds in dollars.
public class Trader implements Writable {
    private static int nextAccountId = 0;
    private int id;
    private String name;
    private Portfolio portfolio;
    private double funds;
    private static final double initialFunds = 5000.00;

    // REQUIRES: name is not blank
    // EFFECTS: constructs a new trader with their name, an empty list of stocks,
    // a unique id, and initial funds (set to 5000 dollars)
    public Trader(String name) {
        this.name = name;
        this.portfolio = new Portfolio();
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

    public ArrayList<Stock> getPortfolioStocks() {
        return portfolio.getPortfolio();
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    // sets portfolio for testing
    public void setPortfolio(Portfolio p) {
        this.portfolio = p;
    }

    public void setFunds(double f) {
        this.funds = f;
    }

    public void addStock(Stock s) {
        portfolio.addStock(s);
    }


    // REQUIRES: funds >= stock price * buyAmount
    // MODIFIES: this
    // EFFECTS: stock is bought and updated in portfolio, user funds are updated
    public void buyStock(Stock stock, int buyAmount) {
        double price = stock.getPrice() * buyAmount;
        funds -= price;
        portfolio.addStock(stock, buyAmount);
        EventLog.getInstance().logEvent(new Event(this.name + " bought " + buyAmount + " share(s) of "
                + stock.getSymbol()));
    }

    // REQUIRES: sellAmount <= s.getAmount()
    // MODIFIES: this
    // EFFECTS: stock is sold and updated in portfolio, user funds are updated
    public void sellStock(Stock stock, int sellAmount) {
        double price = stock.getPrice() * sellAmount;
        funds += price;
        portfolio.subtractStock(stock, sellAmount);
        EventLog.getInstance().logEvent(new Event(this.name + " sold " + sellAmount + " share(s) of "
                + stock.getSymbol()));
    }

    // REQUIRES: amount > 0, amount <= 5000
    // MODIFIES: this
    // EFFECTS: adds amount of funds to trader's funds on hand
    public void addFunds(double amount) {
        funds += amount;
        EventLog.getInstance().logEvent(new Event(this.name + " added " + "$" + amount + " to balance"));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("funds", funds);
        json.put("portfolio", portfolioToJson());
        return json;
    }

    // EFFECTS: returns stocks in this portfolio as a JSON array
    private JSONArray portfolioToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Stock s : portfolio.getPortfolio()) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }




}
