package ui;

import model.ListOfBooking;
import model.Booking;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {

    private static final String JSON_STORE = "./data/bookings.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 550;
    protected ListOfBooking listOfBooking;
    private JTextArea textArea;
    private JPanel textPanel;
    private JPanel toolArea;
    private final Font textFont = new Font("Helvetica",Font.PLAIN,16);
    private final List<Tool> tools = new ArrayList<>();

    private JLabel name;
    private AddBookingNameField addNameField;
    private JLabel phoneNum;
    private AddBookingPhoneNumField addPhoneNumField;
    private JLabel time;
    private AddBookingTimeField addTimeField;
    private AddTool add;
    private ViewByDayTool viewByDay;
    private JLabel dayChoose;

    private JComboBox day;
    private final String select = "--SELECT--";
    private final String monday = "MONDAY";
    private final String tuesday = "TUESDAY";
    private final String wednesday = "WEDNESDAY";
    private final String thursday = "THURSDAY";
    private final String friday = "FRIDAY";
    private final String saturday = "SATURDAY";
    private final String sunday = "SUNDAY";
    private final String[] days = {select, monday, tuesday, wednesday, thursday, friday, saturday, sunday};

    private JComboBox viewDay;
    private final String[] viewDays = {select, monday, tuesday, wednesday, thursday, friday, saturday, sunday};




    public GUI() {
        super("Booking application");
        listOfBooking = new ListOfBooking();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window where this bookingApp will operate, and makes the tools to be used
    //          on the booking list
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        createTextPanel();
        createTools();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the area where all messages will be printed to
    private void createTextPanel() {
        textPanel = new JPanel();
        textArea = new JTextArea();
        textArea.setFont(textFont);
        textPanel.setPreferredSize(new Dimension(WIDTH * 2 / 3, HEIGHT));
        Color c = new Color(232, 209, 93);
        textPanel.setBackground(c);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        setPreferredSize(new Dimension(WIDTH * 2 / 3, HEIGHT));


        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(WIDTH * 2 / 3 - 10, HEIGHT - 100));

        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        printCurrent();

        textPanel.add(scroll);

        add(textPanel, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: a helper method which declares and instantiates all tools
    private void createTools() {
        setUpCreateTools();

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        JPanel addPanel = new JPanel();
        createAddPanel(addPanel);
        toolArea.add(addPanel, gc);

        gc.gridy = 3;
        JPanel viewByDayPanel = new JPanel();
        createViewByDayPanel(viewByDayPanel);
        toolArea.add(viewByDayPanel, gc);

        gc.gridy = 4;
        ViewTool viewCurrent = new ViewCurrentTool(this, toolArea, gc);
        tools.add(viewCurrent);

        gc.gridy = 5;
        ViewTool viewByLoading = new ViewByLoadingTool(this, toolArea, gc);
        tools.add(viewByLoading);

        gc.gridy = 6;
        Tool save = new SaveTool(this, toolArea, gc);
        tools.add(save);
    }

    // MODIFIES: this
    // EFFECTS: set up the panel and area
    private void setUpCreateTools() {
        Container toolContainer = getContentPane();
        toolArea = new JPanel();
        toolArea.setLayout(new GridBagLayout());
        toolArea.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
        toolContainer.add(toolArea, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: create the panel for to view the bookings in the selected day
    private void createViewByDayPanel(JPanel viewByDayPanel) {
        GridBagConstraints viewPanelConstraints = setUpCreateAddPanel(viewByDayPanel, "Select day to view:", 10);

        dayChoose = new JLabel("Day: ");
        viewPanelConstraints.anchor = GridBagConstraints.LINE_END;
        viewPanelConstraints.gridx = 0;
        viewPanelConstraints.gridy = 0;
        viewByDayPanel.add(dayChoose, viewPanelConstraints);

        viewDayComboBox(viewByDayPanel, viewPanelConstraints);
    }

    // MODIFIES: this
    // EFFECTS: enables the view selected day button when the combo box is not "--SELECT--"
    private void viewDayComboBox(JPanel viewByDayPanel, GridBagConstraints viewPanelConstraints) {
        viewDay = new JComboBox(viewDays);
        viewDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox dayBox = (JComboBox)e.getSource();
                String selected = (String)dayBox.getSelectedItem();
                if (selected.equals(select)) {
                    viewByDay.setEnabled(false);
                } else {
                    viewByDay.setEnabled(true);
                }
            }
        });
        viewPanelConstraints.anchor = GridBagConstraints.BASELINE;
        viewPanelConstraints.gridx = 1;
        viewByDayPanel.add(viewDay, viewPanelConstraints);

        viewPanelConstraints.gridy = 1;
        viewByDay = new ViewByDayTool(this, viewByDayPanel, viewPanelConstraints);
    }

    // MODIFIES: this
    // EFFECTS: prints the list of bookings on a specific day if the booking exist on that day
    public void doViewBookings(String viewDay) {
        String bookingInfo = "";
        if (listOfBooking.getSize() > 0) {
            bookingInfo = bookingInfo + "Bookings on " + viewDay + ":\n";
            int count = 0;
            for (int i = 0; i < listOfBooking.getSize(); i++) {
                Booking b = listOfBooking.getBookingInfo(i);
                if (viewDay.equals(b.getDay())) {
                    count += 1;
                    bookingInfo = new StringBuilder().append(bookingInfo).append("Booking number ")
                            .append(count).append(":\n").append("Name: ").append(b.getName()).append("\n")
                            .append("Phone Number: ").append(b.getPhoneNumber()).append("\n").append("Booking Time: ")
                            .append(b.getTime()).append("\n")
                            .append("-----------------------------------------------------------------\n").toString();
                }
            }
        } else {
            bookingInfo = bookingInfo + "Booking does not exist on " + viewDay;
        }
        setText(bookingInfo);
    }

    // MODIFIES: this
    // EFFECTS: constructs the area that booking can be added
    private void createAddPanel(JPanel addPanel) {
        GridBagConstraints addPanelConstraints = setUpCreateAddPanel(addPanel, "Add an Item:", 0);

        addPanelNameField(addPanel, addPanelConstraints);

        addPanelPhoneNumField(addPanel, addPanelConstraints);

        addPanelTimeField(addPanel, addPanelConstraints);

        day = new JComboBox(days);
        day.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox dayBox = (JComboBox)e.getSource();
                String selected = (String)dayBox.getSelectedItem();
                if (selected.equals(select)) {
                    add.setEnabled(false);
                } else {
                    add.setEnabled(true);
                }
            }
        });
        addPanelConstraints.anchor = GridBagConstraints.BASELINE;
        addPanelConstraints.gridy = 3;
        addPanel.add(day, addPanelConstraints);

        addPanelConstraints.gridy = 4;
        add = new AddTool(this, addPanel, addPanelConstraints);

    }

    // MODIFIES: this
    // EFFECTS: constructs the time field in the area where booking can be added
    private void addPanelTimeField(JPanel addPanel, GridBagConstraints addPanelConstraints) {
        time = new JLabel("Time: ");
        addPanelConstraints.anchor = GridBagConstraints.LINE_END;
        addPanelConstraints.gridx = 0;
        addPanelConstraints.gridy = 2;
        addPanel.add(time, addPanelConstraints);

        addPanelConstraints.anchor = GridBagConstraints.LINE_START;
        addPanelConstraints.gridx = 1;
        addTimeField = new AddBookingTimeField(this, addPanel, addPanelConstraints);
    }

    // MODIFIES: this
    // EFFECTS: constructs the phone number field in the area where booking can be added
    private void addPanelPhoneNumField(JPanel addPanel, GridBagConstraints addPanelConstraints) {
        phoneNum = new JLabel("Phone number: ");
        addPanelConstraints.anchor = GridBagConstraints.LINE_END;
        addPanelConstraints.gridx = 0;
        addPanelConstraints.gridy = 1;
        addPanel.add(phoneNum, addPanelConstraints);

        addPanelConstraints.anchor = GridBagConstraints.LINE_START;
        addPanelConstraints.gridx = 1;
        addPhoneNumField = new AddBookingPhoneNumField(this, addPanel, addPanelConstraints);
    }

    // MODIFIES: this
    // EFFECTS: constructs the name field in the area where booking can be added
    private void addPanelNameField(JPanel addPanel, GridBagConstraints addPanelConstraints) {
        name = new JLabel("Name: ");
        addPanelConstraints.anchor = GridBagConstraints.LINE_END;
        addPanelConstraints.gridx = 0;
        addPanelConstraints.gridy = 0;
        addPanel.add(name, addPanelConstraints);

        addPanelConstraints.anchor = GridBagConstraints.LINE_START;
        addPanelConstraints.gridx = 1;
        addNameField = new AddBookingNameField(this, addPanel, addPanelConstraints);
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel to add booking
    private GridBagConstraints setUpCreateAddPanel(JPanel addPanel, String s, int i) {
        addPanel.setLayout(new GridBagLayout());
        addPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(s),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        GridBagConstraints addPanelConstraints = new GridBagConstraints();
        addPanelConstraints.weightx = 0.5;
        addPanelConstraints.weighty = 0.5;
        addPanelConstraints.insets = new Insets(0, 0, i, 0);
        return addPanelConstraints;
    }

    // MODIFIES: this
    // EFFECTS: loads booking list from file
    public void loadBookingList() {
        try {
            listOfBooking = jsonReader.read();
            System.out.println("Current bookings: " + JSON_STORE);
            setText("Loaded bookings");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the booking list to file
    public void saveBookingList() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfBooking);
            jsonWriter.close();
            System.out.println("Saved bookings to " + JSON_STORE);
            setText("Saved bookings");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: return all the bookings in the booking list
    public String printAll() {
        String bookings = "";
        for (int i = 0; i < listOfBooking.getSize(); i++) {
            int bookingNum = i + 1;
            Booking b = listOfBooking.getBookingInfoWithKey(listOfBooking.getBookingInfo(i).getName());

            bookings = bookings + "Booking #" + bookingNum + ":\nName: " + b.getName() + "\n" + "Phone Number: "
                    + b.getPhoneNumber() + "\n" + "Booking Time: " + b.getTime() + "\n" + "Booking Day: "
                    + b.getDay() + "\n-----------------------------------------------------------------\n";
        }
        return bookings;
    }

    // MODIFIES: this
    // EFFECTS: print all the bookings in the booking list and set text to currentBookings
    public void printCurrent() {
        String currentBookings = "Current bookings: \n\n" + printAll();
        setText(currentBookings);
    }

    // EFFECTS: gets the text in the name field in the add area
    public String getAddName() {
        return addNameField.getFieldText();
    }

    // EFFECTS: gets the text in the phone number field in the add area
    public String getAddPhoneNum() {
        return addPhoneNumField.getFieldText();
    }

    // EFFECTS: gets the text in the time field in the add area
    public String getAddTime() {
        return addTimeField.getFieldText();
    }

    // MODIFIES: this
    // EFFECTS: sets the field in the add area to empty
    public void setAddField() {
        addNameField.setEmpty();
        addPhoneNumField.setEmpty();
        addTimeField.setEmpty();
    }

    // MODIFIES: this
    // EFFECTS: adds the given Booking to the booking list
    public void addBooking(Booking b) {
        String booking = "Name: " + b.getName() + "\t" + "Phone Number: " + b.getPhoneNumber() + "\t"
                + "Booking Time: " + b.getTime() + "\t" + "Booking Day: " + b.getDay() + "\t\n";
        listOfBooking.addBookingInfo(b);
        setText("The following booking has been added to the list: \n" + booking);
    }

    // EFFECTS: gets the item type the combo box is set on
    public String getSelected() {
        return (String)day.getSelectedItem();
    }

    // EFFECTS: gets the item type the combo box is set on
    public String getSelectedDay() {
        return (String)viewDay.getSelectedItem();
    }

    // MODIFIES: this
    // EFFECTS: resets the combo box value
    public void setItemType() {
        day.setSelectedIndex(0);
    }

    // MODIFIES: this
    // EFFECTS: sets the text in the text area to the given text
    public void setText(String txt) {
        textArea.setText(txt);
    }





}
