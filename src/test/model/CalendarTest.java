package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarTest {
    MindDay md1;
    MindDay md2;
    MindDay md3;
    MindDay md4;
    MindDay md5;
    MindDay md6;
    Calendar cal1;
    Calendar cal2;


    @BeforeEach
    void runBefore() {
        md1 = new MindDay(true, "sad");
        md2 = new MindDay(false, "sad");
        md3 = new MindDay(true, "sad");
        md4 = new MindDay(false, "sad");
        md5 = new MindDay(true, "happy");
        md6 = new MindDay(true, "happy");

        this.cal1 = new Calendar();
        this.cal2 = new Calendar();
    }

    @Test
    void testMedStreakAtTheEnd() {
        md1.addMedication("x");
        md2.addMedication("x");
        md3.addMedication("y");
        md4.addMedication("x");
        md5.addMedication("x");
        md6.addMedication("x");

        cal1.addMD(md1);
        cal1.addMD(md2);
        cal1.addMD(md3);
        cal1.addMD(md4);
        cal1.addMD(md5);
        cal1.addMD(md6);

        assertEquals(3, cal1.medicationStreak("x"));
    }

    @Test
    void testMedStreakAtTheStart() {
        md1.addMedication("x");
        md2.addMedication("x");
        md3.addMedication("x");
        md4.addMedication("y");
        md5.addMedication("x");

        cal1.addMD(md1);
        cal1.addMD(md2);
        cal1.addMD(md3);
        cal1.addMD(md4);
        cal1.addMD(md5);

        assertEquals(3, cal1.medicationStreak("x"));
    }

    @Test
    void testAddMindDay() {
        cal2.addMD(md1);
        assertEquals(1, cal2.calendarSize());
        cal2.addMD(md2);
        assertEquals(2, cal2.calendarSize());
    }

    @Test
    void testWereMedsTaken() {
        cal1.addMD(md1);
        cal1.addMD(md2);
        cal1.addMD(md3);
        cal1.addMD(md4);

        assertTrue(cal1.wereMedsTaken(1));
        assertFalse(cal1.wereMedsTaken(2));
    }

    @Test
    void testMoodyDays() {
        cal1.addMD(md1);
        cal1.addMD(md2);
        cal1.addMD(md3);
        cal1.addMD(md4);
        cal1.addMD(md5);
        cal1.addMD(md6);

        assertEquals(4, cal1.moodyDays("sad"));
        assertEquals(2, cal1.moodyDays("happy"));
    }

    @Test
    void testMoodyDaysMixedUp() {
        cal1.addMD(new MindDay(true, "upset"));
        cal1.addMD(new MindDay(true, "ecstatic"));
        cal1.addMD(new MindDay(true, "angry"));
        cal1.addMD(new MindDay(true, "upset"));
        cal1.addMD(new MindDay(true, "upset"));
        cal1.addMD(new MindDay(true, "ecstatic"));

        assertEquals(3, cal1.moodyDays("upset"));
        assertEquals(2, cal1.moodyDays("ecstatic"));
        assertEquals(1, cal1.moodyDays("angry"));
    }

    @Test
    void testMoodyDaysNoMoodFound() {
        cal1.addMD(md1);
        cal1.addMD(md2);

        assertEquals(0, cal1.moodyDays("rage"));
    }

    @Test
    void testChangeTakenMedsOfOneDay() {
        cal1.addMD(md1);
        assertTrue(md1.getTakenMeds());
        cal1.changeTakenMeds(1);
        assertFalse(md1.getTakenMeds());
        cal1.changeTakenMeds(1);
        assertTrue(md1.getTakenMeds());
    }

    @Test
    void testChangeTakenMedsOfMultipleDays() {
        cal1.addMD(md1);
        cal1.addMD(md2);
        assertFalse(md2.getTakenMeds());
        assertTrue(md1.getTakenMeds());
        cal1.changeTakenMeds(1);
        cal1.changeTakenMeds(2);
        assertFalse(md1.getTakenMeds());
        assertTrue(md2.getTakenMeds());
    }


}
