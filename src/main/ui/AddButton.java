package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Represents a button to create a new purchase
public class AddButton extends Button {

    // EFFECTS: constructs a button on given parent with given tracker
    public AddButton(TrackerAppGUI tracker, JComponent parent) {
        super(tracker, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a "Create new purchase" button
    @Override
    protected void createButton() {
        button = new JButton("Add new purchase");
    }

    // EFFECTS: opens a create purchase menu
    @Override
    public void actionPerformed(ActionEvent e) {
        new AddMenu(tracker.getPurchaseLog());
    }
}
