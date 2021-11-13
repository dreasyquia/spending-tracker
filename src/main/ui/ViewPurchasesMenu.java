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
public class ViewPurchasesMenu extends JFrame implements ActionListener {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    private PurchaseLog purchaseLog;

    // EFFECTS: constructs a view purchases menu
    public ViewPurchasesMenu(PurchaseLog purchaseLog) {
        this.purchaseLog = purchaseLog;

        setUpFrame();
        displayPurchases();
        addCloseButton();

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
    // EFFECTS: adds close button frame
    private void addCloseButton() {
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        add(closeButton, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: closes frame
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}
