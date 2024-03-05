package persistence;

import org.json.JSONObject;

/**
 * Inspired by Paul Carter's JsonSerializationDemo, https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 * Forces classes that implement this interface to implement a toJson method.
 */
public interface Writable {
    // EFFECTS: returns this class as JSON object
    JSONObject toJson();
}