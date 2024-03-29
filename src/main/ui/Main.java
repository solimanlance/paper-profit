package ui;

//import ui.gui.StockMarketGuiApp;
import ui.console.StockMarketConsoleApp;
import ui.gui.SplashScreen;
import ui.gui.StockMarketFrame;

public class Main {
    public static void main(String[] args) {
        //new StockMarketConsoleApp();
        new SplashScreen();
        new StockMarketFrame();
    }

}
