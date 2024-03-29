package ui.gui;

import javax.swing.*;
import java.awt.*;

// displays users portfolio
public class ViewPortfolioPage extends JPanel {
    private JLabel stockLabel1;
    private JLabel stockLabel2;
    private JLabel stockLabel3;

    public ViewPortfolioPage() {
        JLabel label = new JLabel("Portfolio");
        label.setFont(new Font("Helvetica Neue", Font.BOLD,25));

        setLayout(new GridBagLayout());

        addComponentToGridWithX(label, 0,2,0,0);
        addStockLabels();

    }

    // TODO add logic
    // MODIFIES: this
    // EFFECTS: initializes stock labels and adds to grid
    private void addStockLabels() {
        stockLabel1 = new JLabel("Stock 1");
        stockLabel2 = new JLabel("Stock 2");
        stockLabel3 = new JLabel("Stock 3");
        addComponentToGridWithX(stockLabel1, 0,1,0,1);
        addComponentToGridWithX(stockLabel2, 0,1,0,2);
        addComponentToGridWithX(stockLabel3, 0,1,0,3);
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

}
