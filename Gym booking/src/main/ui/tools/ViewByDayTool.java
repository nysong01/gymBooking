package ui.tools;

import ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewByDayTool extends ViewTool {

    public ViewByDayTool(GUI bookingApp, JComponent parent, GridBagConstraints gc) {
        super(bookingApp, parent, gc);
        button.setEnabled(false);
    }

    // EFFECTS: returns the string "View selected day"
    @Override
    protected String getLabel() {
        return "View selected day";
    }

    // MODIFIES: this
    // EFFECTS: constructs a listener object and add it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new ViewByDayToolClickHandler());
    }

    private class ViewByDayToolClickHandler implements ActionListener {

        // EFFECTS: when button pressed, make a sound and print all the bookings in selected day
        @Override
        public void actionPerformed(ActionEvent e) {
            String viewDay = bookingApp.getSelectedDay();
            Toolkit.getDefaultToolkit().beep();
            bookingApp.doViewBookings(viewDay);
        }
    }
}
