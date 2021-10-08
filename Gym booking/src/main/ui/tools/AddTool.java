package ui.tools;

import model.Booking;
import ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTool extends Tool {
    private String select = "--SELECT--";

    public AddTool(GUI bookingApp, JComponent parent, GridBagConstraints gc) {
        super(bookingApp, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a new button
    @Override
    protected void createButton() {
        button = new JButton("Add");
        button.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: constructs a listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new AddToolClickHandler());
    }


    private class AddToolClickHandler implements ActionListener {

        // MODIFIES: ListOfBooking
        // EFFECTS: when button is pressed, makes a sound, add booking if the day is not equal to SELECT
        //          set add field, and reset the fields
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            String name = bookingApp.getAddName();
            String phoneNum = bookingApp.getAddPhoneNum();
            String time = bookingApp.getAddTime();
            String day = bookingApp.getSelected();

            if (!day.equals(select)) {
                Booking booking = new Booking(name, phoneNum, time, day);
                bookingApp.addBooking(booking);
            }

            bookingApp.setAddField();
            bookingApp.setItemType();

        }
    }
}
