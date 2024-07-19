package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// a calendar made up of MindDays
public class Calendar implements Writable {
    private List<MindDay> calendar;

    // EFFECTS: constructs a calendar with no MindDays added
    public Calendar() {
        calendar = new ArrayList<>();
    }

    // EFFECTS: adds a MindDay to the calendar
    //MODIFIES: this.calendar
    public void addMD(MindDay mindDay) {
        this.calendar.add(mindDay);
        EventLog.getInstance().logEvent(new Event("Added new Mind Day to Calendar."));
    }

    // EFFECTS: given a day, returns a true if meds were taken, and false if meds were not taken
    // REQUIRES: the day provided must be greater than 0
    public boolean wereMedsTaken(int day) {
        day--;
        return calendar.get(day).getTakenMeds();
    }

    // EFFECTS: when given a mood, returns how many days that person had that mood for
    public int moodyDays(String mood) {
        int count = 0;
        for (MindDay day : calendar) {
            if (mood.equals(day.getMood())) {
                count++;
            }
        }
        EventLog.getInstance().logEvent(new Event("Checked your mood-streak."));
        return count;
    }

    // EFFECTS: changes the medication status of a given day
    // MODIFIES: getTakenMeds() status
    // REQUIRES: the day provided must be greater than 0
    public void changeTakenMeds(int day) {
        day--;
        calendar.get(day).setTakenMeds(!calendar.get(day).getTakenMeds());
    }

    // EFFECTS: returns the longest streak in days (int) someone has taken a given medication
    public int medicationStreak(String medication) {
        int streak = 0;
        int oldStreak = 0;
        for (MindDay day : calendar) {
            if (day.getMedications().contains(medication)) {
                streak++;
            } else {
                if (streak >= oldStreak) {
                    oldStreak = streak;
                }
                streak = 0;
            }
        }
        if (streak >= oldStreak) {
            return streak;
        } else {
            return oldStreak;
        }
    }

    //EFFECTS: returns the number of days in a given calendar
    public int calendarSize() {
        return calendar.size();
    }

    // EFFECTS: returns an unmodifiable list of MindDays in a given calendar
    public List<MindDay> getMindDays() {
        return Collections.unmodifiableList(calendar);
    }

    @Override
    // credits to JsonSerializationDemo
    // EFFECTS: takes a Calendar and interprets it as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("calendar", calendarToJson());
        return json;
    }

    // credits to JsonSerializationDemo
    // EFFECTS: returns MindDays in this calendar as a JSON array
    private JSONArray calendarToJson() {
        JSONArray jsonArray = new JSONArray();

        for (MindDay md : calendar) {
            jsonArray.put(md.toJson());
        }

        return jsonArray;
    }

}
