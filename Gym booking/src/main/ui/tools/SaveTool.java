package ui.tools;

import model.Booking;
import ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveTool extends Tool {

    public SaveTool(GUI bookingApp, JComponent parent, GridBagConstraints gc) {
        super(bookingApp, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a new button
    @Override
    protected void createButton() {
        button = new JButton("Save bookings");
        button.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new SaveToolClickHandler());
    }


    private class SaveToolClickHandler implements ActionListener {

        // MODIFIES: ListOfBooking
        // EFFECTS: when button is pressed, make a sound and save bookings to the booking list
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            bookingApp.saveBookingList();


        }
    }
}
