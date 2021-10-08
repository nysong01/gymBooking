package ui.tools;

import ui.GUI;

import javax.swing.*;
import java.awt.*;

public abstract class TextField {

    GUI bookingApp;
    JTextField textField;

    private Font textFieldFont = new Font("Helvetica",Font.PLAIN,16);

    public TextField(GUI bookingApp, JComponent parent, GridBagConstraints gc) {
        this.bookingApp = bookingApp;
        textField = new JTextField(10);
        textField.setFont(textFieldFont);
        addToParent(parent, gc);
    }

    // MODIFIES: parent
    // EFFECTS: add the text field to the parent component with the given constraints
    private void addToParent(JComponent parent, GridBagConstraints gc) {
        parent.add(textField, gc);
    }



    // EFFECTS: return the string in the text field
    public String getFieldText() {
        return textField.getText();
    }

    // MODIFIES: this
    // EFFECT: sets the text field empty
    public void setEmpty() {
        textField.setText("");
    }
}
