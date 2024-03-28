package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

// add funds panel
public class AddFundsPage extends JPanel implements FocusListener {

    private String defaultAddFundsText = "Enter Amount:";
    private JTextField addFundsTextField;

    public AddFundsPage() {
        JLabel label = new JLabel("Add Funds", SwingConstants.CENTER);
        addFundsTextField = new JTextField(16);
        JButton submitButton = new JButton("Submit");

        addListeners(); // for greyed text
        setGhostText(); // setting ui of ghost text
        setLayout(new GridBagLayout());

        this.addComponentToGrid(label, 0, 0, 0);
        this.addComponentToGrid(addFundsTextField,0,0,2);
        this.addComponentToGrid(submitButton,0,0,5);

        setFonts(label, submitButton);
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
        addFundsTextField.setText(defaultAddFundsText);
        addFundsTextField.setForeground(Color.LIGHT_GRAY);
    }

    // MODIFIES: this
    // EFFECTS: adds listeners to text field(s)
    private void addListeners() {
        addFundsTextField.addFocusListener(this);
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
        if (field == addFundsTextField && field.getText().equals(defaultAddFundsText)) {
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
            if (field == addFundsTextField) {
                field.setText(defaultAddFundsText);
            }
            field.setForeground(Color.LIGHT_GRAY);
        }
    }

}

