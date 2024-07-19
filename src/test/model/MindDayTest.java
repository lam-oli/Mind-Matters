package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MindDayTest {
    private MindDay md1;

    @BeforeEach
    public void runBefore() {
        md1 = new MindDay(true, "happy");
        md1.addMedication("melatonin");
    }

    @Test
    void testGetMood() {
        assertEquals("happy", md1.getMood());
    }

    @Test
    void testGetTakenMeds() {
        assertTrue(md1.getTakenMeds());
    }

    @Test
    void testSetTakenMeds() {
        md1.setTakenMeds(false);
        assertFalse(md1.getTakenMeds());
    }

    @Test
    void testGetMeds() {
        assertEquals("melatonin", md1.getMedications().get(0));
        assertEquals(1, md1.getMedications().size());
    }

    @Test
    void testAddMeds() {
        md1.addMedication("adderall");
        assertEquals("melatonin", md1.getMedications().get(0));
        assertEquals("adderall", md1.getMedications().get(1));
        assertEquals(2, md1.getMedications().size());
    }


}
