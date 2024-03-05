package persistence;

import model.Trader;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Inspired by Paul Carter's JsonSerializationDemo, https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 * Writes JSON representation of a trader to file
 */
public class JsonWriter {

    private final int indentNum = 3;
    private PrintWriter writer;
    private String filePath;

    public JsonWriter(String destination) {
        this.filePath = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer for writing, throws FileNotFoundException if
    // destination file can't be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(filePath));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON of users trader to file
    public void write(Trader t) {
        JSONObject json = t.toJson();
        saveToFile(json.toString(indentNum));
    }

    // MODIFIES: this
    // EFFECTS: closes file reference
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
