package ui;

import model.Date;
import model.Purchase;
import model.PurchaseLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Represents a menu that allows user to view all purchases
public class ViewMenu extends JFrame implements ActionListener {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    private PurchaseLog purchaseLog;
    private JPanel buttonPanel;
    private JButton clearPurchasesButton;
    private JButton closeButton;

    // EFFECTS: constructs a view purchases menu
    public ViewMenu(PurchaseLog purchaseLog) {
        this.purchaseLog = purchaseLog;

        setUpFrame();
        displayPurchases();
        addButtonPanel();

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up frame
    private void setUpFrame() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: if no purchases have been made, adds label saying so, otherwise displays all recorded purchases
    private void displayPurchases() {

        List<Purchase> purchaseHistory = purchaseLog.getPurchaseHistory();

        if (purchaseHistory.isEmpty()) {
            JLabel noPurchasesMadeLabel = new JLabel("No purchases have been recorded.", SwingConstants.CENTER);
            add(noPurchasesMadeLabel, BorderLayout.CENTER);
        } else {
            JLabel purchaseTitle = new JLabel("Purchases:", SwingConstants.CENTER);
            add(purchaseTitle, BorderLayout.PAGE_START);

            List<String> stringList = new ArrayList<>();
            for (Purchase p : purchaseHistory) {
                Date date = p.getPurchaseDate();
                stringList.add("Name: " + p.getName() + ", Price: " + p.getPrice() + ", Date of Purchase: "
                        + date.getDay() + "/" + date.getMonth() + "/" + date.getYear() + ", Category: "
                        + p.getCategory());
                JList list = new JList(stringList.toArray());
                list.setVisibleRowCount(10);
                JScrollPane scrollPane = new JScrollPane(list);

                add(scrollPane, BorderLayout.CENTER);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds panel to bottom of frame with "Clear purchases" and "Close" buttons
    public void addButtonPanel() {
        buttonPanel = new JPanel();
        addClearPurchasesButton();
        addCloseButton();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: adds clear purchases button to frame
    private void addClearPurchasesButton() {
        clearPurchasesButton = new JButton("Clear purchases");
        clearPurchasesButton.addActionListener(this);
        buttonPanel.add(clearPurchasesButton);
    }

    // MODIFIES: this
    // EFFECTS: adds close button to frame
    private void addCloseButton() {
        closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        buttonPanel.add(closeButton);
    }

    // MODIFIES: this
    // EFFECTS: clears purchases and opens new ViewPurchasesMenu, closes frame otherwise
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearPurchasesButton) {
            purchaseLog.clear();
            dispose();
            new ViewMenu(purchaseLog);
        } else {
            dispose();
        }
    }
}
