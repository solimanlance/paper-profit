package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Stock market GUI application
public class StockMarketFrame extends JFrame implements ActionListener {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public StockMarketFrame() {
        initUi();
    }

    // MODIFIES: this
    // EFFECTS: initializes user interface
    private void initUi() {

        // TODO: these have to be persistent elements, that DONT change with a page change.
        JLabel mainTitle = new JLabel("Stock Manager", SwingConstants.CENTER);
        // TODO placeholder for money, replace with var later.
        JLabel projectLabel = new JLabel("a CPSC 210 project", SwingConstants.CENTER);
        JLabel userBalance = new JLabel("$" + "100,000", SwingConstants.CENTER);


        addPanels();
        //getContentPane().add(cardPanel, BorderLayout.NORTH); // adds panels to frame
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

        cardPanel.add(new LoginPage(), "View Market");
        cardPanel.add(new ViewStockPage(), "View Market");
        cardPanel.add(new ViewPortfolioPage(), "View Portfolio");
        cardPanel.add(new BuyPage(), "Buy Stock");
        cardPanel.add(new SellPage(), "Sell Stock");
        cardPanel.add(new AddFundsPage(), "Add Funds");
        cardPanel.add(new SavePage(), "Save/Load");
    }

    // MODIFIES: this
    // EFFECTS: adds view menu buttons
    private void addViewMenuButtons(JMenu menu) {
        JMenuItem viewMarketButton = new JMenuItem("View Market");
        JMenuItem viewPortfolioButton = new JMenuItem("View Portfolio");

        viewMarketButton.addActionListener(this);
        viewPortfolioButton.addActionListener(this);

        menu.add(viewMarketButton);
        menu.add(viewPortfolioButton);
    }

    // MODIFIES: this
    // EFFECTS: adds manage menu buttons
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
    // EFFECTS: sets fonts
    private void setFonts(JLabel mainTitle, JLabel userBalance, JLabel projectLabel) {
        mainTitle.setFont(new Font("Helvetica Neue", Font.ITALIC,14));
        userBalance.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
        projectLabel.setFont(new Font("Helvetica Neue", Font.PLAIN,8));
        projectLabel.setForeground(Color.GRAY);
    }

    // MODIFIES: this
    // EFFECTS: sets grid for center elements
    private void setGrid(JLabel mainTitle, JLabel userBalance, JLabel balanceLabel) {
        addComponentToGrid(userBalance, 50, 0, 0);
        addComponentToGrid(mainTitle, 15, 0, 0);
        addComponentToGrid(balanceLabel, 140, 0, 1);
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
        JMenuItem menuItem = (JMenuItem) e.getSource();
        String menuText = menuItem.getText();

        cardLayout.show(cardPanel, menuText);
    }
}
