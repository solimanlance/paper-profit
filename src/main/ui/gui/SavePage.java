package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

// save page panel
public class SavePage extends JPanel implements FocusListener {
    private String defaultNameText = "Enter Name:";
    private JTextField nameField;

    public SavePage()  {
        JLabel label = new JLabel("Save/Load", SwingConstants.CENTER);
        nameField = new JTextField(16);
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        addListeners(); // for greyed text
        setGhostText(); // setting ui of ghost text
        setLayout(new GridBagLayout());

        this.addComponentToGridWithX(label, 0, 2, 0,0);
        this.addComponentToGridWithX(nameField,0,2,0,2);
        this.addComponentToGridWithX(saveButton,0,1,0,5);
        this.addComponentToGridWithX(loadButton,0,1,1,5);

        setFonts(label, saveButton);
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
    // EFFECTS: sets text and font colour of ghost text in field
    private void setGhostText() {
        nameField.setText(defaultNameText);
        nameField.setForeground(Color.LIGHT_GRAY);
    }

    // MODIFIES: this
    // EFFECTS: adds listeners to text field(s)
    private void addListeners() {
        nameField.addFocusListener(this);
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
        if (field == nameField && field.getText().equals(defaultNameText)) {
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
            if (field == nameField) {
                field.setText(defaultNameText);
            }
            field.setForeground(Color.LIGHT_GRAY);
        }
    }

}

