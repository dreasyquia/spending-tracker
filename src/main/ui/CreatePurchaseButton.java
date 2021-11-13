package ui;

import model.PurchaseCategory;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Represents a button to create a new purchase
public class CreatePurchaseButton extends Button {

    // EFFECTS: constructs a button on given parent with given tracker
    public CreatePurchaseButton(TrackerAppGUI tracker, JComponent parent) {
        super(tracker, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a "Create new purchase" button
    @Override
    protected void createButton() {
        button = new JButton("Create new purchase");
    }

    // EFFECTS: opens a create purchase menu
    @Override
    public void actionPerformed(ActionEvent e) {
        new CreatePurchaseMenu(tracker.getPurchaseLog());
    }
}
