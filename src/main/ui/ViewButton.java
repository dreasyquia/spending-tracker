package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Represents a button to view all purchases
public class ViewButton extends Button {

    // EFFECTS: constructs a button on given parent with given tracker
    public ViewButton(TrackerAppGUI tracker, JComponent parent) {
        super(tracker, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a "View all purchases" button
    @Override
    protected void createButton() {
        button = new JButton("View all purchases");
    }

    // EFFECTS: opens a view purchases menu
    @Override
    public void actionPerformed(ActionEvent e) {
        new ViewMenu(tracker.getPurchaseLog());
    }
}
