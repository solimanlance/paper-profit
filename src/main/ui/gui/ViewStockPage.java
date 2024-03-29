package ui.gui;

import model.Stock;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

// displays users market
public class ViewStockPage extends JPanel {
    private StockMarketFrame frame;
    private Stock stock1;
    private Stock stock2;
    private Stock stock3;

    public ViewStockPage(StockMarketFrame frame) {
        JLabel label = new JLabel("Stock Market", SwingConstants.CENTER);
        label.setFont(new Font("Helvetica Neue", Font.BOLD,25));
        this.frame = frame;

        setLayout(new GridBagLayout());

        addComponentToGridWithX(label, 0,2,0,0);

        initStocks(); // initializes stocks
        frame.setMarket(stock1, stock2, stock3);

        addStockLabels();
    }

    // MODIFIES: this
    // EFFECTS: initializes stock labels and adds to grid
    private void addStockLabels() {
        JLabel stockLabel1 = new JLabel(stock1.getSymbol(), SwingConstants.CENTER);
        JLabel stockLabel2 = new JLabel(stock2.getSymbol(), SwingConstants.CENTER);
        JLabel stockLabel3 = new JLabel(stock3.getSymbol(), SwingConstants.CENTER);
        addComponentToGridWithX(stockLabel1, 20,1,0,1);
        addComponentToGridWithX(stockLabel2, 0,1,0,2);
        addComponentToGridWithX(stockLabel3, 0,1,0,3);

        JLabel price1 = new JLabel("$" + Double.toString(stock1.getPrice()), SwingConstants.CENTER);
        JLabel price2 = new JLabel("$" + Double.toString(stock2.getPrice()), SwingConstants.CENTER);
        JLabel price3 = new JLabel("$" + Double.toString(stock3.getPrice()), SwingConstants.CENTER);
        addComponentToGridWithX(price1, 20,1,1,1);
        addComponentToGridWithX(price2, 0,1,1,2);
        addComponentToGridWithX(price3, 0,1,1,3);
    }

    // MODIFIES: this
    // EFFECTS: adds component to grid with given constraints
    private void addComponentToGridWithX(Component component, int topInset, int gridWidth, int gridX, int gridY) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;  // Anchor to the top
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(topInset, 0, 0, 0);
        constraints.gridwidth = gridWidth;
        constraints.weightx = 0.5; // distribute weight equally
        constraints.weighty = 1;
        constraints.gridx = gridX; // always in first column
        constraints.gridy = gridY;
        this.add(component, constraints);
    }

    // MODIFIES: this
    // EFFECTS: initializes stocks with random fluctuation
    private void initStocks() {
        stock1 = new Stock("AMZN", 150 + getFluctuation());
        stock2 = new Stock("META", 500 + getFluctuation());
        stock3 = new Stock("NVDA", 700 + getFluctuation());
    }

    // EFFECTS: fluctuates stocks by + or - 5
    private double getFluctuation() {
        Random random = new Random();
        double fluctuate = random.nextDouble() * 10 - 5; // fluctuates stock by + or -5
        return Math.round(fluctuate * 100.0) / 100.0; // rounds to 2 decimal places
    }

}
