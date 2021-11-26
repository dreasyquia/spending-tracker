package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Represents a button to calculate user's total spending in given month
public class CalculateButton extends Button {

    // EFFECTS: constructs a button on given parent with given tracker
    public CalculateButton(TrackerAppGUI tracker, JComponent parent) {
        super(tracker, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a "Calculate monthly spending" button
    @Override
    protected void createButton() {
        button = new JButton("Calculate monthly spending");
    }

    // EFFECTS: opens a calculate spending menu
    @Override
    public void actionPerformed(ActionEvent e) {
        new CalculateMenu(tracker.getPurchaseLog());
    }
}
