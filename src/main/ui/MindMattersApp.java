package ui;


import model.Calendar;
import model.MindDay;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Mind Matters application
// credit to Teller App
public class MindMattersApp {
    private static final String JSON_STORE = "./data/MindDayCalendar.json";
    private Calendar calendar;
    private Scanner input;
    private String mood;
    private boolean takenMeds;
    private String medication;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs Mind Matters application
    public MindMattersApp() {
        runMMApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runMMApp() {
        boolean keepGoing = true;
        input = new Scanner(System.in);

        init();

        while (keepGoing) {
            displayMenu();
            int command = input.nextInt();
            if (command == 8) {
                keepGoing = false;

            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThank you for updating!");
        System.out.println("\nHave a great Day!");
    }

    // MODIFIES: this
    // EFFECTS: initializes calendar
    public void init() {
        calendar = new Calendar();
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(int command) {
        if (command == 1) {
            doAddMindDay();
        } else if (command == 2) {
            doWereMedsTaken();
        } else if (command == 3) {
            doMoodyDays();
        } else if (command == 4) {
            doMedicationStreak();
        } else if (command == 5) {
            doChangeTakenMedsStatus();
        } else if (command == 6) {
            doSaveMindDay();
        } else if (command == 7) {
            doLoadMindDay();
        } else if (command == 8) {
            System.out.println("Done!");
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads previous Mind Days
    private void doLoadMindDay() {
        jsonReader = new JsonReader(JSON_STORE);

        try {
            calendar = jsonReader.read();
            System.out.println("Loaded Mind Day from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Saves Mind Days
    private void doSaveMindDay() {
        jsonWriter = new JsonWriter(JSON_STORE);

        try {
            jsonWriter.open();
            jsonWriter.write(calendar);
            jsonWriter.close();
            System.out.println("Saved Mind Day to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

    // MODIFIES: this
    // EFFECTS: conducts the change of a medication status of a given day in the calendar
    private void doChangeTakenMedsStatus() {
        System.out.println("Select a day you would like to see if you took your medication:");
        this.input = new Scanner(System.in);
        int number = this.input.nextInt();
        calendar.changeTakenMeds(number);
        System.out.println("You changed your medication status!");
    }

    // MODIFIES: this
    // EFFECTS: conducts a count of the longest streak of a given medication in the calendar
    private void doMedicationStreak() {
        System.out.println("Enter a medication to see your longest streak:");
        this.input = new Scanner(System.in);
        String selectedMeds = this.input.next();
        int medDays = calendar.medicationStreak(selectedMeds.toLowerCase());
        System.out.println("Your longest streak is: " + medDays + " days");
    }

    // MODIFIES: this
    // EFFECTS: conducts a count of a given mood in the calendar
    private void doMoodyDays() {

        System.out.println("Enter a mood to see how many calendar days you felt this way:");

        this.input = new Scanner(System.in);
        String selectedMood = this.input.next();
        int moodyDays = calendar.moodyDays(selectedMood.toLowerCase());
        System.out.println("You felt this mood: " + moodyDays + " days");
    }

    // MODIFIES: this
    // EFFECTS: conducts if medication was taken on a given day
    private void doWereMedsTaken() {
        System.out.println("Select a day you would like to see if you took your meds:");
        this.input = new Scanner(System.in);
        int number = this.input.nextInt();
        boolean wereMedsTaken = calendar.wereMedsTaken(number);

        if (wereMedsTaken == true) {
            System.out.println("Yes, you did take your meds!");
        } else {
            System.out.println("Oh no, you did not take your meds!");
        }

    }

    // MODIFIES: this
    // EFFECTS: conducts an addition of an updated MindDay to the calendar
    private void doAddMindDay() {

        System.out.println("Lets start a new MindDay:");

        System.out.println("Type out your mood:");
        this.input = new Scanner(System.in);
        String mood = this.input.next();
        this.mood = mood.toLowerCase();

        System.out.println("Type out your medication:");
        this.input = new Scanner(System.in);
        String meds = this.input.next();
        this.medication = meds.toLowerCase();

        System.out.println("Did you take your medication(s) today?");
        System.out.println("Select a number:");
        System.out.println("1: Yes");
        System.out.println("2: No");
        this.input = new Scanner(System.in);
        int num = this.input.nextInt();
        if (num == 1) {
            this.takenMeds = true;
        } else if (num == 2) {
            this.takenMeds = false;
        }

        MindDay md1 = new MindDay(takenMeds, mood);
        calendar.addMD(md1);
        md1.addMedication(medication);

    }

    // EFFECTS: displays menu of options to user
    public void displayMenu() {
        System.out.println("\nWelcome to Mind Matters, lets update your day!");
        System.out.println("\nSelect a number:");
        System.out.println("\t1: Add a new Mind Day!");
        System.out.println("\t2: Select a day to see if you took your medications.");
        System.out.println("\t3: See how many days you felt a certain mood.");
        System.out.println("\t4: Select to see your longest taken-medication streak.");
        System.out.println("\t5: Select to change whether you took your meds on a given day.");
        System.out.println("\t6: Select to save your Mind Day.");
        System.out.println("\t7: Select to load your Mind Day.");
        System.out.println("\t8: Quit");
    }


}
