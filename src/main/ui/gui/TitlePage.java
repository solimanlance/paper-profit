package ui.gui;

import javax.swing.*;
import java.awt.*;

public class TitlePage extends JFrame {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    public TitlePage() {
        initUi();
    }

    private void initUi() {

        JLabel mainTitle = new JLabel("Stock Manager", SwingConstants.CENTER);
        // placeholder for money, replace with var later.
        JLabel userBalance = new JLabel("$" + "100,000", SwingConstants.CENTER);
        JButton stockBuyButton = new JButton("Buy Stock");

        this.setLayout(new GridBagLayout());

        setFonts(mainTitle, userBalance, stockBuyButton);
        setGrid(mainTitle, userBalance, stockBuyButton);
        addButtons();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);
        this.setTitle("Paper Profit");
        this.setVisible(true);
    }

    private void addButtons() {
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Menu");
        JMenu viewSubMenu = new JMenu("View");
        JMenu manageSubMenu = new JMenu("Manage");
        JMenu saveSubMenu = new JMenu("Save/Load");

        optionsMenu.add(viewSubMenu);
        optionsMenu.add(manageSubMenu);
        optionsMenu.add(saveSubMenu);

        addViewMenuButtons(viewSubMenu);
        addManageMenuButtons(manageSubMenu);
        addSaveMenuButtons(saveSubMenu);

        menuBar.add(optionsMenu);
        this.setJMenuBar(menuBar);
    }


    private void addViewMenuButtons(JMenu menu) {
        JMenuItem viewMarketButton = new JMenuItem("View Market");
        JMenuItem viewPortfolioButton = new JMenuItem("View Portfolio");

        menu.add(viewMarketButton);
        menu.add(viewPortfolioButton);
    }

    private void addManageMenuButtons(JMenu menu) {
        JMenuItem viewBuyButton = new JMenuItem("Buy Stock");
        JMenuItem viewSellButton = new JMenuItem("Sell Stock");

        menu.add(viewBuyButton);
        menu.add(viewSellButton);
    }

    private void addSaveMenuButtons(JMenu menu) {
        JMenuItem saveButton = new JMenuItem("Save");
        JMenuItem loadButton = new JMenuItem("Load");

        menu.add(saveButton);
        menu.add(loadButton);
    }

    private void setFonts(JLabel mainTitle, JLabel userBalance, JButton stockBuyButton) {
        mainTitle.setFont(new Font("Helvetica Neue", Font.PLAIN,14));
        userBalance.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        stockBuyButton.setFont(new Font("Helvetica Neue", Font.PLAIN,12));
    }

    private void setGrid(JLabel mainTitle, JLabel userBalance, JButton stockBuyButton) {
        addComponentToGrid(userBalance, 0, 0, 0);
        addComponentToGrid(mainTitle, 10, 0, 1);
        addComponentToGrid(stockBuyButton, 0, 0, 2);
    }

    private void addComponentToGrid(Component component, int topInset, int leftInset, int gridY) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(topInset, leftInset, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = gridY;
        this.add(component, constraints);
    }
}
