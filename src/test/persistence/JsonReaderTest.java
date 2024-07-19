package persistence;

import model.Calendar;
import model.MindDay;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Class credits to JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Calendar calendar = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyCalendar.json");
        try {
            Calendar calendar = reader.read();
            assertEquals(0, calendar.calendarSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralCalendar.json");
        try {
            Calendar calendar = reader.read();

            List<String> med1 = new ArrayList<>();
            med1.add("adderall");
            List<String> med2 = new ArrayList<>();
            med1.add("xanax");

            List<MindDay> mindDays = calendar.getMindDays();
            assertEquals(2, mindDays.size());
            checkMindDay(true, "happy", mindDays.get(0));
            checkMindDay(true, "sad", mindDays.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
