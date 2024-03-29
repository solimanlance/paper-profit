package ui.gui;

import javax.swing.*;
import java.awt.*;

// displays loading screen gif
public class SplashScreen {
    private JWindow splashScreen;

    public SplashScreen() {
        splashScreen = new JWindow();
        showSplashScreen();

        try {
            Thread.sleep(3000); // shows splash screen for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        closeSplashScreen();
    }

    // MODIFIES: this
    // EFFECTS: initializes and displays loading screen gif
    public void showSplashScreen() {
        splashScreen = new JWindow();
        splashScreen.setSize(700, 500);
        JLabel splashLabel = new JLabel(new ImageIcon("img/paperprofitsplash.gif"));
        JLabel title = new JLabel("Paper Profit");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
        splashScreen.getContentPane().add(title, BorderLayout.CENTER);
        splashScreen.getContentPane().add(splashLabel, BorderLayout.CENTER);

        splashScreen.pack();
        splashScreen.setLocationRelativeTo(null); // centers the splash screen on the screen
        splashScreen.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: finds splash screen window and closes it
    public void closeSplashScreen() {
        // find and close the splash screen window
        if (splashScreen != null) {
            splashScreen.dispose();
        }
    }
}
