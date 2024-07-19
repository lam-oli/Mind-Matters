package persistence;

import model.MindDay;
import org.json.JSONObject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Class credits to JsonSerializationDemo
public class JsonTest {

    protected void checkMindDay(boolean takenMeds, String mood, MindDay mindDay) {
        assertEquals(mood, mindDay.getMood());
        assertEquals(takenMeds, mindDay.getTakenMeds());
    }
}