package ui.gui;

import model.Trader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

// buy page panel
public class BuyPage extends JPanel implements FocusListener {

    private String defaultSymbolText = "Enter Symbol:";
    private String defaultAmountText = "Enter Amount:";
    private JTextField symbolTextField;
    private JTextField amountTextField;
    private StockMarketFrame frame;

    public BuyPage(StockMarketFrame frame) {
        this.frame = frame;
        JLabel label = new JLabel("Buy Stock", SwingConstants.CENTER);
        symbolTextField = new JTextField(16);
        amountTextField = new JTextField(16);
        JButton submitButton = new JButton("Submit");

        addListeners(); // for greyed text
        setGhostText(); // setting ui of ghost text
        setLayout(new GridBagLayout());

        this.addComponentToGrid(label, 0, 0, 0);
        this.addComponentToGrid(symbolTextField,0,0,2);
        this.addComponentToGrid(amountTextField,0,0,4);
        this.addComponentToGrid(submitButton,0,0,5);

        setFonts(label, submitButton);
        submitButton.addActionListener(createSubmitButtonListener());
    }

    // MODIFIES: this
    // EFFECTS: adds component to grid with given constraints
    private void addComponentToGrid(Component component, int topInset, int leftInset, int gridY) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;  // Anchor to the top
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(topInset, leftInset, 0, 0);
        constraints.weighty = 1;
        constraints.gridx = 0; // always in first column
        constraints.gridy = gridY;
        this.add(component, constraints);
    }

    // MODIFIES: this
    // EFFECTS: sets text and font colour of ghost text in field
    private void setGhostText() {
        symbolTextField.setText(defaultSymbolText);
        amountTextField.setText(defaultAmountText);
        symbolTextField.setForeground(Color.LIGHT_GRAY);
        amountTextField.setForeground(Color.LIGHT_GRAY);
    }

    // MODIFIES: this
    // EFFECTS: adds listeners to text field(s)
    private void addListeners() {
        symbolTextField.addFocusListener(this);
        amountTextField.addFocusListener(this);
    }

    // MODIFIES: this
    // EFFECTS: sets fonts to elements that need special fonts
    private void setFonts(JLabel label, JButton submitButton) {
        label.setFont(new Font("Helvetica Neue", Font.BOLD,25));
        submitButton.setFont(new Font("Helvetica Neue", Font.PLAIN,12));
    }

    // MODIFIES: this
    // EFFECTS: sets text field to blank if selected
    @Override
    public void focusGained(FocusEvent e) {
        JTextField field = (JTextField) e.getComponent();
        if (field == symbolTextField && field.getText().equals(defaultSymbolText)) {
            field.setText("");
            field.setForeground(Color.BLACK);
        } else if (field == amountTextField && field.getText().equals(defaultAmountText)) {
            field.setText("");
            field.setForeground(Color.BLACK);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets text field to instruction if deselected -- ghost text
    @Override
    public void focusLost(FocusEvent e) {
        JTextField field = (JTextField) e.getComponent();
        if (field.getText().isEmpty()) {
            if (field == symbolTextField) {
                field.setText(defaultSymbolText);
            } else if (field == amountTextField) {
                field.setText(defaultAmountText);
            }
            field.setForeground(Color.LIGHT_GRAY);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles submit button press, processes stock purchase
    private ActionListener createSubmitButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String symbol = symbolTextField.getText();

                try {
                    int amount = Integer.valueOf(amountTextField.getText());
                } catch (NumberFormatException ex) {
                    frame.errorMessage("amount needs to be a number", "Amount Not Number");
                }
                int amount = Integer.valueOf(amountTextField.getText());

                symbolTextField.setText("");
                amountTextField.setText("");

                frame.buyStock(symbol, amount);
                frame.updateUserBalance();
            }
        };
    }
}

