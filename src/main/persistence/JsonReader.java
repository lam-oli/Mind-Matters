package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Calendar;
import model.Event;
import model.EventLog;
import model.MindDay;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
// Class credits to JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads calendar from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Calendar read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Loaded Mind Days from Calendar"));
        return parseCalendar(jsonObject);

    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses calendar from JSON object and returns it
    private Calendar parseCalendar(JSONObject jsonObject) {
        Calendar calendar = new Calendar();
        addMindDays(calendar, jsonObject);
        return calendar;
    }


    // MODIFIES: calendar
    // EFFECTS: parses MindDays from JSON object and adds them to calendar
    private void addMindDays(Calendar calendar, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("calendar");
        for (Object json : jsonArray) {
            JSONObject nextDay = (JSONObject) json;
            addMindDay(calendar, nextDay);
        }
    }

    // MODIFIES: calendar
    // EFFECTS: parses a MindDay from JSON object and adds it to calendar
    private void addMindDay(Calendar calendar, JSONObject jsonObject) {
        boolean takenMeds = jsonObject.getBoolean("takenMeds");
        String mood = jsonObject.getString("mood");
        MindDay mindDay = new MindDay(takenMeds, mood);
        calendar.addMD(mindDay);
    }
}

