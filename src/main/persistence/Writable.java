package persistence;

import org.json.JSONObject;

// CITATION: based on Writable in JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
