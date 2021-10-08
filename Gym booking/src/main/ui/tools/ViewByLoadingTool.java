package ui.tools;

import ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewByLoadingTool extends ViewTool {

    public ViewByLoadingTool(GUI bookingApp, JComponent parent, GridBagConstraints gc) {
        super(bookingApp, parent, gc);
    }

    // EFFECTS: returns the string "Load Booking"
    @Override
    protected String getLabel() {
        return "Load Booking";
    }

    // MODIFIES: this
    // EFFECTS: constructs a listener object and add it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new ViewByLoadingToolClickHandler());
    }

    private class ViewByLoadingToolClickHandler implements ActionListener {

        // EFFECTS: when button pressed, make a sound and load the bookings
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            bookingApp.loadBookingList();
        }
    }
}
