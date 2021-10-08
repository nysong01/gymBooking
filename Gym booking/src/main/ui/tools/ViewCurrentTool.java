package ui.tools;

import ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCurrentTool extends ViewTool {

    public ViewCurrentTool(GUI bookingApp, JComponent parent, GridBagConstraints gc) {
        super(bookingApp, parent, gc);
    }

    // EFFECTS: returns the string "View Current"
    @Override
    protected String getLabel() {
        return "View Current";
    }

    // MODIFIES: this
    // EFFECTS: constructs a listener object and add it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new ViewCurrentToolClickHandler());
    }

    private class ViewCurrentToolClickHandler implements ActionListener {

        // EFFECTS: when button pressed, make a sound and print all the current bookings
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            bookingApp.printCurrent();
        }
    }


}
