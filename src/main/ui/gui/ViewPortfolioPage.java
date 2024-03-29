package ui.gui;

import model.Stock;

import javax.swing.*;
import java.awt.*;

// displays users portfolio
public class ViewPortfolioPage extends JPanel {
    private StockMarketFrame frame;

    public ViewPortfolioPage(StockMarketFrame frame) {
        this.frame = frame;
        JLabel label = new JLabel("Portfolio", SwingConstants.CENTER);
        label.setFont(new Font("Helvetica Neue", Font.BOLD,25));

        setLayout(new GridBagLayout());

        addComponentToGridWithX(label, 0,3,0,0);
        updateStockLabels();

    }

    // MODIFIES: this
    // EFFECTS: initializes stock labels and adds to grid
    public void updateStockLabels() {
        removeAll();
        setTitle();
        if (frame.getTrader() != null && frame.getTrader().getPortfolioSize() != 0) {
            JLabel total = new JLabel("Portfolio Value: $"
                    +  String.format("%.2f", frame.getTrader().getPortfolioValue()), SwingConstants.CENTER);
            int counter = 1;
            for (Stock s : frame.getTrader().getPortfolioStocks()) {
                JLabel stockLabel = new JLabel(s.getSymbol(), SwingConstants.CENTER);
                JLabel price = new JLabel("$" + Double.toString(s.getPrice()), SwingConstants.CENTER);
                JLabel amount = new JLabel(Integer.toString(s.getAmount()) + " share(s);", SwingConstants.CENTER);
                if (counter == 1) {
                    addComponentToGridWithX(stockLabel, 20, 1, 0, 1);
                    addComponentToGridWithX(amount, 20, 1, 1, 1);
                    addComponentToGridWithX(price, 20, 1, 2, 1);
                } else {
                    addComponentToGridWithX(stockLabel, 0, 1, 0, counter);
                    addComponentToGridWithX(amount, 0, 1, 1, counter);
                    addComponentToGridWithX(price, 0, 1, 2, counter);
                }
                counter++;
            }
            addComponentToGridWithX(total, 20, 3, 0, counter + 1);
        }
    }

    private void setTitle() {
        JLabel label = new JLabel("Portfolio", SwingConstants.CENTER);
        label.setFont(new Font("Helvetica Neue", Font.BOLD,25));

        setLayout(new GridBagLayout());

        addComponentToGridWithX(label, 0,3,0,0);
    }

    // MODIFIES: this
    // EFFECTS: adds component to grid with given constraints
    private void addComponentToGridWithX(Component component, int topInset, int gridWidth, int gridX, int gridY) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;  // Anchor to the top
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(topInset, 0, 0, 0);
        constraints.gridwidth = gridWidth;
        constraints.weightx = 1; // distribute weight equally
        constraints.weighty = 1;
        constraints.gridx = gridX; // always in first column
        constraints.gridy = gridY;
        this.add(component, constraints);
    }

}
