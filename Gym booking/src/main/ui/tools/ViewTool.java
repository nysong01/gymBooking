package ui.tools;

import ui.GUI;

import javax.swing.*;
import java.awt.*;

public abstract class ViewTool extends Tool {

    public ViewTool(GUI bookingApp, JComponent parent, GridBagConstraints gc) {
        super(bookingApp, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a button with the label provided
    @Override
    protected void createButton() {
        button = new JButton(getLabel());
    }

    // EFFECTS: returns the label of the button
    protected abstract String getLabel();

}
