package ui;

import model.PurchaseLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

// Represents a menu that allows user to calculate their total spending in a given month
public class CalculateSpendingMenu extends JFrame implements ActionListener {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;

    private PurchaseLog purchaseLog;
    private JSpinner dateSpinner;

    // EFFECTS: constructs a calculate spending menu
    public CalculateSpendingMenu(PurchaseLog purchaseLog) {
        this.purchaseLog = purchaseLog;
        setUpFrame();
        addDateSpinner();
        addSubmitButton();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds submit button to frame
    private void addSubmitButton() {
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton);
    }

    // MODIFIES: this
    // EFFECTS: adds date spinner to frame
    private void addDateSpinner() {
        SpinnerModel model = new SpinnerDateModel(new Date(), null, null, Calendar.YEAR);
        dateSpinner = new JSpinner(model);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "MM/yyyy"));

        add(dateSpinner);
    }

    // MODIFIES: this
    // EFFECTS: sets up frame
    private void setUpFrame() {
        setLayout(new GridLayout(0, 1));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: closes frame, gets date from date spinner, and displays total spending in given month
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        SpinnerModel dateModel = dateSpinner.getModel();
        Date date = ((SpinnerDateModel) dateModel).getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        double total = purchaseLog.calculateMoneySpentInMonth(month, year);

        JOptionPane.showMessageDialog(null, "Total spending in " + month + "/" + year
                + ": $" + total);

    }
}
