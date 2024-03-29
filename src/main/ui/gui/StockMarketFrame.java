package ui.gui;

import model.Stock;
import model.Trader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// stock market GUI application
public class StockMarketFrame extends JFrame implements ActionListener {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JLabel userBalance;
    private JLabel dynamicText;
    private ViewPortfolioPage portfolioPage;
    private Trader trader;
    private Stock stock1;
    private Stock stock2;
    private Stock stock3;
    private ArrayList<Stock> stockList;

    public StockMarketFrame() {
        initUi();
    }

    // MODIFIES: this
    // EFFECTS: initializes user interface
    private void initUi() {
        // persistent elements here:
        JLabel mainTitle = new JLabel("Stock Manager", SwingConstants.CENTER);
        JLabel projectLabel = new JLabel("a CPSC 210 project", SwingConstants.CENTER);
        userBalance = new JLabel("$" + "5000.00", SwingConstants.CENTER); // set for initial state


        addPanels();
        this.setLayout(new GridBagLayout());
        addComponentToGrid(cardPanel, 150, 10, 0);

        setFonts(mainTitle, userBalance, projectLabel);
        setGrid(mainTitle, userBalance, projectLabel);
        addButtons();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);
        this.setTitle("Paper Profit");
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds menu bar to title page
    private void addButtons() {
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Menu");
        JMenu viewSubMenu = new JMenu("View");
        JMenu manageSubMenu = new JMenu("Manage");
        JMenuItem saveButton = new JMenuItem("Save/Load");
        saveButton.addActionListener(this);

        optionsMenu.add(viewSubMenu);
        optionsMenu.add(manageSubMenu);
        optionsMenu.add(saveButton);

        addViewMenuButtons(viewSubMenu);
        addManageMenuButtons(manageSubMenu);

        menuBar.add(optionsMenu);
        this.setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: adds panels (each different page) to ui
    private void addPanels() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        this.portfolioPage = new ViewPortfolioPage(this);
        cardPanel.add(new LoginPage(this), "View Market");
        cardPanel.add(new ViewStockPage(this), "View Market");
        cardPanel.add(portfolioPage, "View Portfolio");
        cardPanel.add(new BuyPage(this), "Buy Stock");
        cardPanel.add(new SellPage(this), "Sell Stock");
        cardPanel.add(new AddFundsPage(this), "Add Funds");
        cardPanel.add(new SavePage(this), "Save/Load");
    }

    // MODIFIES: this
    // EFFECTS: adds view menu buttons to menu bar
    private void addViewMenuButtons(JMenu menu) {
        JMenuItem viewMarketButton = new JMenuItem("View Market");
        JMenuItem viewPortfolioButton = new JMenuItem("View Portfolio");

        viewMarketButton.addActionListener(this);
        viewPortfolioButton.addActionListener(this);

        menu.add(viewMarketButton);
        menu.add(viewPortfolioButton);
    }

    // MODIFIES: this
    // EFFECTS: adds manage menu buttons to menu bar
    private void addManageMenuButtons(JMenu menu) {
        JMenuItem viewBuyButton = new JMenuItem("Buy Stock");
        JMenuItem viewSellButton = new JMenuItem("Sell Stock");
        JMenuItem viewAddFundsButton = new JMenuItem("Add Funds");

        viewBuyButton.addActionListener(this);
        viewSellButton.addActionListener(this);
        viewAddFundsButton.addActionListener(this);

        menu.add(viewBuyButton);
        menu.add(viewSellButton);
        menu.add(viewAddFundsButton);
    }


    // MODIFIES: this
    // EFFECTS: sets fonts to elements that need special font styles
    private void setFonts(JLabel mainTitle, JLabel userBalance, JLabel projectLabel) {
        mainTitle.setFont(new Font("Helvetica Neue", Font.ITALIC,14));
        userBalance.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
        projectLabel.setFont(new Font("Helvetica Neue", Font.PLAIN,8));
        projectLabel.setForeground(Color.GRAY);
    }

    // MODIFIES: this
    // EFFECTS: sets grid for center elements
    private void setGrid(JLabel mainTitle, JLabel userBalance, JLabel projectLabel) {
        addComponentToGrid(userBalance, 50, 0, 0);
        addComponentToGrid(mainTitle, 15, 0, 0);
        addComponentToGrid(projectLabel, 140, 0, 1);
    }

    // MODIFIES: this
    // EFFECTS: adds component to grid with given constraints
    private void addComponentToGrid(Component component, int topInset, int leftInset, int gridY) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;  // Anchor to the top
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(topInset, leftInset, 0, 0);
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = gridY;
        this.add(component, constraints);
    }

    // MODIFIES: this
    // EFFECTS: shows selected panel on click
    @Override
    public void actionPerformed(ActionEvent e) {
        if (trader != null) {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            String menuText = menuItem.getText();

            if (menuText == "View Portfolio") {
                portfolioPage.updateStockLabels();
            }

            cardLayout.show(cardPanel, menuText);
            dynamicText.setText("Welcome, " + trader.getName() + ".");
        } else {
            errorMessage("login first pls", "No User Found");
        }
    }

    // MODIFIES: this
    // EFFECTS: displays pop up error when something wrong is done
    public void errorMessage(String msg, String type) {
        JOptionPane optionPane = new JOptionPane(msg, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog(type);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets current page to stock page after login
    public void exitLoginPage() {
        cardLayout.show(cardPanel, "View Market");

        dynamicText = new JLabel("Welcome, " + trader.getName() + ".", SwingConstants.CENTER);
        dynamicText.setFont(new Font("Helvetica Neue", Font.ITALIC,12));
        dynamicText.setForeground(Color.GRAY);
        addComponentToGrid(dynamicText, 130, 0, 0);
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    // MODIFIES: this
    // EFFECTS: initializes stock market
    public void setMarket(Stock s1, Stock s2, Stock s3) {
        stock1 = s1;
        stock2 = s2;
        stock3 = s3;

        stockList = new ArrayList<>();
        stockList.add(s1);
        stockList.add(s2);
        stockList.add(s3);
    }

    // MODIFIES: this
    // EFFECTS: conducts a stock being bought
    public void buyStock(String symbol, int amount) {
        int counter = 0;
        boolean foundSymbol = false;
        for (Stock s : stockList) {
            if (s.getSymbol().equals(symbol)) {
                if ((s.getPrice() * amount) > trader.getFunds()) {
                    errorMessage("too expensive", "Not Enough Funds");
                } else {
                    dynamicText.setText(amount + " stocks of " + symbol + " successfully bought.");
                    trader.buyStock(stockList.get(counter), amount);
                    foundSymbol = true;
                }
            }
            counter++;
        }

        if (!foundSymbol) {
            errorMessage("symbol not found", "Invalid Symbol");
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a stock being sold
    public void sellStock(String symbol, int amount) {
        int counter = 0;
        boolean foundSymbol = false;
        if (trader.getPortfolioSize() == 0) {
            errorMessage("you own no stock", "Portfolio Empty");
        }
        for (Stock s : stockList) {
            if (s.getSymbol().equals(symbol)) {
                if (amount > trader.getPortfolio().getStock(symbol).getAmount()) {
                    foundSymbol = true; // just to not get below error
                    errorMessage("too much", "Not Enough Shares owned");
                } else {
                    dynamicText.setText(amount + " stocks of " + symbol + " successfully sold.");
                    trader.sellStock(stockList.get(counter), amount);
                    foundSymbol = true;
                }
            }
            counter++;
        }

        if (!foundSymbol) {
            errorMessage("symbol not found", "Invalid Symbol");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds funds to user balance
    public void addFunds(double amount) {
        trader.addFunds(amount);
        dynamicText.setText("Successfully added $" + String.format("%.2f",amount) + " to account.");
    }


    // MODIFIES: this
    // EFFECTS: updates user balance label
    public void updateUserBalance() {
        userBalance.setText("$" + String.format("%.2f",trader.getFunds())); // rounds to 2 decimal places
    }

    public Trader getTrader() {
        return trader;
    }

    public void setDynamicText(String msg) {
        dynamicText.setText(msg);
    }

}
