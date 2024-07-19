package model;


import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// a MindDay to track mood and medications
public class MindDay implements Writable {
    private boolean takenMeds;
    private String mood;
    private List<String> medications;

    // EFFECTS: constructs a MindDay with a mood, if meds were taken or not, and an empty list of medications
    public MindDay(boolean takenMeds, String mood) {
        this.takenMeds = takenMeds;
        this.mood = mood;
        this.medications = new ArrayList<>();
    }

    // EFFECTS: adds a given medication to the list of medications of this MindDay
    // MODIFIES: this.medications
    public void addMedication(String meds) {
        medications.add(meds);
    }

    public String getMood() {
        return mood;
    }

    public boolean getTakenMeds() {
        return takenMeds;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setTakenMeds(boolean medStatus) {
        this.takenMeds = medStatus;
    }

    @Override
    // credits to JsonSerializationDemo
    // EFFECTS: takes a MindDay and interprets it as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("takenMeds", takenMeds);
        json.put("mood", mood);
        json.put("medications", medications);
        return json;
    }

}
