package ui;

import model.Calendar;
import model.Event;
import model.EventLog;
import model.MindDay;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;


//Mind Matter Graphical User Interphase
//credits to LabelChanger application
public class MindMattersGUI extends JFrame implements ActionListener {
    private JFrame newMindDay;
    private JPanel btn1btn2;
    private JPanel btn3btn4;
    private JPanel btn5btn6;
    private JLabel intro;
    private JPanel medStatus;
    private JPanel meds;
    private JPanel mood;
    private JTextField moodText;
    private JTextField moodTextField;
    private JTextField medText;
    private JComboBox medStatusBox;
    private Calendar calendar = new Calendar();

    // EFFECTS: Sets up the interphase for the home screen
    // MODIFIES: this
    public MindMattersGUI() {
        super("Mind Matters");
        this.setBackground(Color.red);
        closeFrame();
        this.setPreferredSize(new Dimension(600, 500));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
        ImageIcon brainy = new ImageIcon("data/CuteGuy.jpg");
        JLabel img = new JLabel(brainy);
        img.setPreferredSize(new Dimension(200, 200));
        this.btn1btn2 = new JPanel();
        this.btn3btn4 = new JPanel();
        this.btn5btn6 = new JPanel();
        this.intro = new JLabel("Hope you are doing well :) Lets see what's on your mind today.");
        addHomeButtons();
        this.add(intro);
        this.add(btn1btn2);
        this.add(btn3btn4);
        this.add(btn5btn6);
        this.add(img);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: closes the home screen and prints the EventLog
    private void closeFrame() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("GUI has been closed.");
                printEventLog();
                System.exit(0);
            }
        });
    }

    // EFFECTS: prints out the events from our EventLog iterator
    public void printEventLog() {
        Iterator iterator = EventLog.getInstance().iterator();

        while (iterator.hasNext()) {
            Event nextEvent = (Event) iterator.next();
            System.out.println(nextEvent);
        }
    }

    // EFFECTS: Creates and adds buttons to individual panels
    // MODIFIES: this
    private void addHomeButtons() {
        JButton jb1 = new JButton("Lets start a new Mind Day!");
        JButton jb2 = new JButton("Would you like to see your mood streak?");
        JButton jb3 = new JButton("Select to save your day!");
        JButton jb4 = new JButton("Select to load your Mind Calendar.");
        btn1btn2.add(jb1);
        btn3btn4.add(jb2);
        btn5btn6.add(jb3);
        btn5btn6.add(jb4);
        jb1.setActionCommand("jb1");
        jb2.setActionCommand("jb2");
        jb3.setActionCommand("jb3");
        jb4.setActionCommand("jb4");
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        jb4.addActionListener(this);
    }

    // EFFECTS: Takes the action of the user and processes it to the right command
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("jb1")) {
            addMindDay();
        } else if (e.getActionCommand().equals("jb2")) {
            moodStreak();
        } else if (e.getActionCommand().equals("jb3")) {
            saveMindDay();
        } else if (e.getActionCommand().equals("jb4")) {
            loadMindDay();
        } else if (e.getActionCommand().equals("jb5")) {
            updateMindDay();
        } else if (e.getActionCommand().equals("jb6")) {
            enterMoodStreak();
        }
    }

    // EFFECTS: Loads prior Mind Days from the calendar in json store
    // MODIFIES: this
    private void loadMindDay() {
        JsonReader jsonReader = new JsonReader("./data/MindDayCalendar.json");

        try {
            calendar = jsonReader.read();
            JLabel yayLoad = new JLabel("Loaded your calendar from: ./data/MindDayCalendar.json");
            this.resetHome(yayLoad);
            loadTable();

        } catch (IOException e) {
            JLabel booLoad = new JLabel("Oh no! Could not load your calendar from: ./data/MindDayCalendar.json");
            this.resetHome(booLoad);
        }
    }

    // EFFECTS: Creates and initializes a table for load to display all the Mind Days of a calendar
    private void loadTable() {
        JFrame viewCalendar = new JFrame();
        viewCalendar.setTitle("Days Loaded");
        viewCalendar.setPreferredSize(new Dimension(600, 200));
        viewCalendar.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
        String[] columns = new String[]{"Mind Day:", "Mood:", "Meds Taken?:"};
        DefaultTableModel defaultTable = new DefaultTableModel(columns, 0);
        JTable table = new JTable(defaultTable);
        defaultTable.addRow(columns);

        int day = 1;
        for (MindDay md : calendar.getMindDays()) {
            int mindDay = day++;
            String mood = md.getMood();
            boolean ifMeds = md.getTakenMeds();
            Object[] row = new Object[]{mindDay, mood, ifMeds};
            defaultTable.addRow(row);
        }
        viewCalendar.add(table);
        viewCalendar.pack();
        viewCalendar.setLocationRelativeTo(null);
        viewCalendar.setVisible(true);
        viewCalendar.setResizable(false);
    }

    // EFFECTS: Saves Mind Days to the calendar in json store
    // MODIFIES: this
    private void saveMindDay() {
        JsonWriter jsonWriter = new JsonWriter("./data/MindDayCalendar.json");

        try {
            jsonWriter.open();
            jsonWriter.write(calendar);
            jsonWriter.close();
            JLabel yaySave = new JLabel("Saved your Mind Day to: ./data/MindDayCalendar.json");
            this.resetHome(yaySave);
        } catch (FileNotFoundException e) {
            JLabel booSave = new JLabel("Oh no! Could not save your Mind Day to: ./data/MindDayCalendar.json");
            this.resetHome(booSave);
        }
    }

    // EFFECTS: Creates the interphase for someone to input their mood for enterMoodStreak()
    private void moodStreak() {
        JFrame moodFrame = new JFrame();
        moodFrame.setTitle("Type What Mood You'd like To See:");
        moodFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
        moodFrame.setPreferredSize(new Dimension(600, 100));

        moodTextField = new JTextField(20);
        JButton moodButton = new JButton("Enter");
        moodFrame.add(moodTextField);
        moodFrame.add(moodButton);
        moodButton.setActionCommand("jb6");
        moodButton.addActionListener(this);

        moodFrame.pack();
        moodFrame.setLocationRelativeTo(null);
        moodFrame.setVisible(true);
        moodFrame.setResizable(false);
    }

    // EFFECTS: Given a mood by user, displays how many times the user felt that way in the calendar
    // MODIFIES: this
    private void enterMoodStreak() {
        int moodyDays = calendar.moodyDays(moodTextField.getText());
        JLabel moodDaysLabel = new JLabel("You have felt this way for this many Mind Days: " + moodyDays);
        this.resetHome(moodDaysLabel);
    }

    // EFFECTS: Creates the interphase for a user to update their Mind Day and displays it
    // MODIFIES: this
    private void addMindDay() {
        newMindDay = new JFrame();
        newMindDay.setPreferredSize(new Dimension(600, 300));
        newMindDay.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
        newMindDay.setTitle("Lets Start A New Day");

        createInput();

        JButton enter = new JButton("Submit my Mind Day!");

        newMindDay.add(mood);
        newMindDay.add(meds);
        newMindDay.add(medStatus);
        newMindDay.add(enter);

        enter.setActionCommand("jb5");
        enter.addActionListener(this);

        newMindDay.pack();
        newMindDay.setLocationRelativeTo(null);
        newMindDay.setVisible(true);
        newMindDay.setResizable(false);
    }

    // EFFECTS: Creates the panels for each Mind Day aspect to update
    private void createInput() {
        mood = new JPanel();
        meds = new JPanel();
        medStatus = new JPanel();

        JLabel moodLabel = new JLabel("Type our what mood you are feeling today: ");
        moodText = new JTextField(20);
        mood.add(moodLabel);
        mood.add(moodText);

        JLabel medLabel = new JLabel("Type out your current medication: ");
        medText = new JTextField(20);
        meds.add(medLabel);
        meds.add(medText);

        JLabel medStatusLabel = new JLabel("Did you take your medications today?");
        String[] options = new String[]{"Yes", "No"};
        medStatusBox = new JComboBox(options);
        medStatus.add(medStatusLabel);
        medStatus.add(medStatusBox);
    }

    // EFFECTS: creates a new Mind Day from user's answers and adds to calendar.
    // MODIFIES: this
    private void updateMindDay() {
        String answer1 = moodText.getText();
        String answer2 = medText.getText();
        Boolean answer3 = toBoolean(medStatusBox.getSelectedItem().toString());

        MindDay md1 = new MindDay(answer3, answer1);
        calendar.addMD(md1);
        md1.addMedication(answer2);
        JLabel updatedDay = new JLabel("Your day has been updated!");
        this.resetHome(updatedDay);
    }

    // EFFECTS: Creates a boolean from a string
    private boolean toBoolean(String input) {
        return input.equals("Yes");
    }

    // EFFECTS: Rests the home screen to its original appearance
    // MODIFIES: this
    private void resetHome(JLabel label) {
        this.getContentPane().removeAll();
        this.add(intro);
        this.add(btn1btn2);
        this.add(btn3btn4);
        this.add(btn5btn6);
        this.add(label);
        this.invalidate();
        this.validate();
    }


}
