package ui;

import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

import static ui.TrackerAppGUI.JSON_STORE;

// Represents a button to load log from file
public class LoadButton extends Button {

    // EFFECTS: constructs a button on given parent with given tracker
    public LoadButton(TrackerAppGUI tracker, JComponent parent) {
        super(tracker, parent);
    }

    // MODIFIES: this
    // EFFECTS: creates a "Load purchase log" button
    @Override
    protected void createButton() {
        button = new JButton("Load purchase log");
    }

    // CITATION: based on loadWorkRoom() in JsonSerializationDemo
    // MODIFIES: tracker
    // EFFECTS: loads purchase log from file
    @Override
    public void actionPerformed(ActionEvent e) {
        JsonReader jsonReader = tracker.getJsonReader();
        try {
            tracker.setPurchaseLog(jsonReader.read());
            JOptionPane.showMessageDialog(null, "Loaded purchase log from " + JSON_STORE);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
        }
    }

}
