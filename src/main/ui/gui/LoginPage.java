package ui.gui;

import javax.swing.*;
import java.awt.*;

// login page panel
public class LoginPage extends JPanel {
    private String defaultNameText = "Enter Name:";
    private JTextField nameField;

    // TODO: ADD logic where buttons in menu cannot be pressed; exception?
    public LoginPage() {
        JLabel label = new JLabel("Login", SwingConstants.CENTER);
        nameField = new JTextField(16);
        JButton submitButton = new JButton("Submit");
        JLabel enter = new JLabel("Enter a username", SwingConstants.CENTER);

        setLayout(new GridBagLayout());

        this.addComponentToGridWithX(label, 0, 2, 0, 0);
        this.addComponentToGridWithX(nameField, 7, 2, 0, 2);
        this.addComponentToGridWithX(submitButton, 0, 1, 0, 5);
        this.addComponentToGridWithX(enter, 15, 1,0,1);
        submitButton.requestFocusInWindow();
        submitButton.grabFocus();

        setFonts(label, submitButton, enter);
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
    // EFFECTS: sets fonts of elements that need special fonts and font colours
    private void setFonts(JLabel label, JButton submitButton, JLabel enter) {
        label.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
        submitButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        enter.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        enter.setForeground(Color.LIGHT_GRAY);
    }
}
