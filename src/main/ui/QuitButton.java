package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Represents a button to quit application
public class QuitButton extends Button {

    // EFFECTS: constructs a button on given parent with given tracker
    public QuitButton(TrackerAppGUI tracker, JComponent parent) {
        super(tracker, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a "Quit" button
    @Override
    protected void createButton() {
        button = new JButton("Quit");
    }

    // EFFECTS: quits application
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
