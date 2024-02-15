package ui;

import model.Portfolio;
import model.Stock;
import model.Trader;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Stock market application
public class StockMarketApp {
    private Portfolio portfolio;
    private Trader trader;
    private Stock stock1;
    private Stock stock2;
    private Stock stock3;
    private ArrayList<Stock> stockList;
    private Scanner input;

    public StockMarketApp() {
        askForName();
    }

    private void askForName() {
        String name;

        input = new Scanner(System.in);

        System.out.print("Enter Name: ");
        name = input.next();

        System.out.println("Welcome, " + name);
        runStockMarket(name);
    }

    // MODIFIES: this
    // EFFECTS: runs stock market application
    private void runStockMarket(String username) {
        boolean hasQuit = false;
        String command;
        init(username);

        while (!hasQuit) {
            displayMenu();
            System.out.println("press q to quit");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                hasQuit = quitConfirmation();
            } else {
                processInput(command);
            }
        }

    }

    // EFFECTS: confirms if user is quitting
    private boolean quitConfirmation() {
        String command = null;

        System.out.println("Are you sure?");
        System.out.println("y/n");
        command = input.next();
        command = command.toLowerCase();

        if (command.equals("y")) {
            System.out.println("bye!");
            return true;
        } else if (command.equals("n")) {
            return false;
        } else {
            System.out.println("not a valid input!");
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes stocks + trader
    private void init(String username) {
        portfolio = new Portfolio();
        trader = new Trader(username, portfolio);
        input = new Scanner(System.in);

        stock1 = new Stock("AMZN", 150 + getFluctuation());
        stock2 = new Stock("META", 500 + getFluctuation());
        stock3 = new Stock("NVDA", 700 + getFluctuation());
        stockList = new ArrayList<>();
        stockList.add(stock1);
        stockList.add(stock2);
        stockList.add(stock3);
    }

    // EFFECTS: fluctuates stocks by + or - 5
    private double getFluctuation() {
        Random random = new Random();
        // fluctuates stock by + or -5
        double fluctuate = random.nextDouble() * 10 - 5;
        // rounds to 2 decimal places
        return Math.round(fluctuate * 100.0) / 100.0;
    }

    // EFFECTS: displays commands to user
    private void displayMenu() {
        System.out.println("Input command:");
        System.out.println("\tvm -> view market");
        System.out.println("\tvf -> view funds");
        System.out.println("\tvp -> view portfolio");
        System.out.println("\tb -> buy stock");
        System.out.println("\ts -> sell stock");
        System.out.println("\ta -> add funds");
    }

    // MODIFIES: this
    // EFFECTS: processes trader input
    private void processInput(String command) {
        if (command.equals("vm")) {
            viewMarket();
        } else if (command.equals("vf")) {
            viewFunds();
        } else if (command.equals("vp")) {
            viewPortfolio();
        } else if (command.equals("b")) {
            buyStock();
        } else if (command.equals("s")) {
            sellStock();
        } else if (command.equals("a")) {
            addFunds();
            System.out.println("sup");
        } else {
            System.out.println("not a valid command");
        }
    }

    // EFFECTS: displays list of stocks and their prices to user
    private void viewMarket() {
        System.out.println("STOCKS:\n");
        for (Stock s : stockList) {
            System.out.println("\t" + s.getSymbol() + ", " + s.getPrice());
        }
        System.out.println("\n");
    }

    // EFFECTS: displays amount of user's funds
    private void viewFunds() {
        System.out.println("\tYou have $" + trader.getFunds() + "!\n");
    }

    // EFFECTS: displays users portfolio
    private void viewPortfolio() {
        System.out.println("PORTFOLIO: \n");
        for (Stock s : portfolio.getPortfolio()) {
            System.out.println("\t" + s.getSymbol() + ", " + s.getPrice() + " AMOUNT: " + s.getAmount());
        }
        System.out.println("\n");
    }

    // MODIFIES: this
    // EFFECTS: conducts a stock transaction
    private void buyStock() {
        System.out.println("Which stock are you buying? Input the symbol please");
        String stockSymbol = input.next();
        System.out.println("How many shares?");
        int amountShares = input.nextInt();
        int counter = 0;

        for (Stock s : stockList) {
            if (s.getSymbol().equals(stockSymbol)) {
                if ((s.getPrice() * amountShares) > trader.getFunds()) {
                    System.out.println("Too expensive!");
                } else {
                    trader.buyStock(stockList.get(counter), amountShares);
                    System.out.println(amountShares + " shares of "
                            + stockList.get(counter).getSymbol() + " successfully bought");
                }
            }
            counter++;
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a stock being sold
    private void sellStock() {
        System.out.println("Which stock are you selling? Input the symbol please");
        String stockSymbol = input.next();
        System.out.println("How many shares?");
        int amountShares = input.nextInt();
        int counter = 0;

        for (Stock s : stockList) {
            if (s.getSymbol().equals(stockSymbol)) {
                if (amountShares > portfolio.getStock(stockSymbol).getAmount()) {
                    System.out.println("Too much!");
                } else {
                    trader.sellStock(stockList.get(counter), amountShares);
                    System.out.println(amountShares + " shares of "
                            + stockList.get(counter).getSymbol() + " successfully sold");
                }
            }
            counter++;
        }
    }

    // MODIFIES: this
    // EFFECTS: increases user funds with given amount
    private void addFunds() {
        System.out.println("How much money do you want?");
        double amount = input.nextDouble();
        double newFunds = trader.getFunds() + amount;
        trader.addFunds(amount);
        System.out.println("Your new balance is $" + newFunds);
    }
}