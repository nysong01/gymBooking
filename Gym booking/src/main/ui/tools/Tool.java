package ui.tools;

import ui.GUI;

import javax.swing.*;
import java.awt.*;

public abstract class Tool {

    protected JButton button;
    protected GUI bookingApp;


    public Tool(GUI bookingApp, JComponent parent, GridBagConstraints gc) {
        this.bookingApp = bookingApp;
        createButton();
        addToParent(parent, gc);
        addListener();
    }

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent, GridBagConstraints gc) {
        parent.add(button, gc);
    }

    // EFFECTS: adds listener for this tool
    protected abstract void addListener();

    // EFFECTS: creates button to activate tool
    protected abstract void createButton();

    // MODIFIES: this
    // EFFECTS: sets the button to enabled
    public void setEnabled(boolean b) {
        button.setEnabled(b);
    }
}
