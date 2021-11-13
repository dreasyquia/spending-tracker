package ui;

import javax.swing.*;
import java.awt.event.ActionListener;

// Represents a menu button
public abstract class Button implements ActionListener {
    protected JButton button;
    protected TrackerAppGUI tracker;
    protected JComponent parent;

    // EFFECTS: constructs a button on given parent with given tracker
    public Button(TrackerAppGUI tracker, JComponent parent) {
        this.tracker = tracker;
        this.parent = parent;
        createButton();
        addToParent(parent);
        addListener();
    }

    // MODIFIES: this
    // EFFECTS: creates a button
    protected abstract void createButton();

    // MODIFIES: this
    // EFFECTS: adds button to parent
    private void addToParent(JComponent parent) {
        parent.add(button);
    }

    // MODIFIES: this
    // EFFECTS: adds listener to button
    private void addListener() {
        button.addActionListener(this);
    }

}
