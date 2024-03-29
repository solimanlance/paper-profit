package ui.gui;

import model.Trader;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


// save/load page panel
public class SavePage extends JPanel {
    private StockMarketFrame frame;
    private String defaultNameText = "Enter Name:";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public SavePage(StockMarketFrame frame)  {
        this.frame = frame;
        JLabel label = new JLabel("Save/Load", SwingConstants.CENTER);
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        setLayout(new GridBagLayout());

        this.addComponentToGridWithX(label, 0, 2, 0,0);
        this.addComponentToGridWithX(saveButton,0,1,0,5);
        this.addComponentToGridWithX(loadButton,0,1,1,5);
        saveButton.addActionListener(createSaveButtonListener());
        loadButton.addActionListener(createLoadButtonListener());

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
    // EFFECTS: sets fonts to elements that need special fonts
    private void setFonts(JLabel label, JButton submitButton) {
        label.setFont(new Font("Helvetica Neue", Font.BOLD,25));
    }

    // MODIFIES: this
    // EFFECTS: handles submit button press, processes save of user state
    private ActionListener createSaveButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = frame.getTrader().getName();
                String jsonPath = "./data/" + name + "-save.json";
                jsonWriter = new JsonWriter(jsonPath);
                jsonReader = new JsonReader(jsonPath);
                try {
                    jsonWriter.open();
                    jsonWriter.write(frame.getTrader());
                    jsonWriter.close();
                    frame.setDynamicText("Saved " + frame.getTrader().getName() + " to " + jsonPath);
                } catch (FileNotFoundException ex) {
                    frame.errorMessage("cant find file", "File Not Found");
                }
                frame.updateUserBalance(); // just in case lol
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: handles submit button press, processes load of user state from json
    private ActionListener createLoadButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = frame.getTrader().getName();
                String jsonPath = "./data/" + name + "-save.json";
                jsonWriter = new JsonWriter(jsonPath);
                jsonReader = new JsonReader(jsonPath);
                try {
                    Trader trader = jsonReader.read();
                    frame.setTrader(trader);
                    frame.setDynamicText("Loaded from " + jsonPath);
                } catch (IOException ex) {
                    frame.errorMessage("Cannot read from file", "IOException");
                }
                frame.updateUserBalance();
            }
        };
    }


}

