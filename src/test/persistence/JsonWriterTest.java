package persistence;

import model.Calendar;
import model.MindDay;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


// Class credits to JsonSerializationDemo
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Calendar calendar = new Calendar();
            JsonWriter writer = new JsonWriter("./happy/data/myCalendar.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Calendar calendar = new Calendar();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCalendar.json");
            writer.open();
            writer.write(calendar);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCalendar.json");
            calendar = reader.read();
            assertEquals(0, calendar.calendarSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Calendar calendar = new Calendar();
            MindDay md1 = new MindDay(true, "happy");
            MindDay md2 = new MindDay(true, "sad");
            md1.addMedication("adderall");
            md1.addMedication("tylenol");
            md2.addMedication("xanax");
            calendar.addMD(md1);
            calendar.addMD(md2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCalendar.json");
            writer.open();
            writer.write(calendar);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCalendar.json");
            calendar = reader.read();
            List<MindDay> mindDays = calendar.getMindDays();
            List<String> medications = md1.getMedications();
            assertEquals(2, medications.size());
            assertEquals(2, calendar.calendarSize());
            checkMindDay(true, "happy", mindDays.get(0));
            checkMindDay(true, "sad", mindDays.get(1));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

