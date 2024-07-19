package persistence;

import org.json.JSONObject;

// Class credits to JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

