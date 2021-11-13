package ui;

import model.Purchase;
import model.PurchaseLog;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import static ui.TrackerAppGUI.JSON_STORE;

// Represents a button to save log to file
public class SaveLogButton extends Button {

    // EFFECTS: constructs a button on given parent with given tracker
    public SaveLogButton(TrackerAppGUI tracker, JComponent parent) {
        super(tracker, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a "Save purchase log" button
    @Override
    protected void createButton() {
        button = new JButton("Save purchase log");
    }

    // CITATION: based on saveWorkRoom() in JsonSerializationDemo
    // EFFECTS: saves the purchase log to file
    @Override
    public void actionPerformed(ActionEvent e) {

        PurchaseLog purchaseLog = tracker.getPurchaseLog();
        JsonWriter jsonWriter = tracker.getJsonWriter();

        try {
            jsonWriter.open();
            jsonWriter.write(purchaseLog);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved purchase log to " + JSON_STORE);
        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE);
        }
    }
}
