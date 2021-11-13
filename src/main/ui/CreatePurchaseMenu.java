package ui;

import model.Purchase;
import model.PurchaseCategory;
import model.PurchaseLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

// Represents a menu that allows user to create a new purchase
public class CreatePurchaseMenu extends JFrame implements ActionListener {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;

    private PurchaseLog purchaseLog;
    private JTextField nameField;
    private JFormattedTextField priceField;
    private JSpinner dateSpinner;
    private JComboBox categoryBox;

    // EFFECTS: constructs a create purchase menu
    public CreatePurchaseMenu(PurchaseLog purchaseLog) {
        this.purchaseLog = purchaseLog;

        setUpFrame();
        addNamePanel();
        addPricePanel();
        addDatePanel();
        addCategoryPanel();
        addSubmitButton();

        setVisible(true);
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
    // EFFECTS: adds name panel to frame
    private void addNamePanel() {
        JPanel namePanel = new JPanel();

        JLabel nameLabel = new JLabel("Name:");
        namePanel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setSize(20, 10);
        namePanel.add(nameField);

        add(namePanel);
    }

    // MODIFIES: this
    // EFFECTS: adds price panel to frame
    private void addPricePanel() {
        JPanel pricePanel = new JPanel();

        JLabel priceLabel = new JLabel("Price:");
        pricePanel.add(priceLabel);

        NumberFormat priceFormat = NumberFormat.getNumberInstance();
        double price = 0.00;
        priceField = new JFormattedTextField(priceFormat);
        priceField.setValue(new Double(price));
        priceField.setColumns(20);

        pricePanel.add(priceField);

        add(pricePanel);
    }

    // MODIFIES: this
    // EFFECTS: adds date panel to frame
    private void addDatePanel() {
        JPanel datePanel = new JPanel();

        JLabel dateLabel = new JLabel("Date of purchase:");
        datePanel.add(dateLabel);

        SpinnerModel model = new SpinnerDateModel(new Date(), null, null, Calendar.YEAR);
        dateSpinner = new JSpinner(model);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));

        datePanel.add(dateSpinner);

        add(datePanel);
    }

    // MODIFIES: this
    // EFFECTS: adds category panel to frame
    private void addCategoryPanel() {
        JPanel categoryPanel = new JPanel();

        JLabel categoryLabel = new JLabel("Category");
        categoryPanel.add(categoryLabel);

        String[] categoryStrings = {"Housing", "Transportation", "Food", "Utilities", "Insurance", "Healthcare",
                "Personal", "Lifestyle", "Entertainment", "Miscellaneous", "Null"};

        categoryBox = new JComboBox(categoryStrings);
        categoryBox.setSelectedIndex(10);

        categoryPanel.add(categoryBox);

        add(categoryPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds submit button to frame
    private void addSubmitButton() {
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton);
    }

    // MODIFIES: this
    // EFFECTS: closes frame, gets data from all fields, creates a new purchase, and adds it to purchase log
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();

        String name = nameField.getText();
        double price = ((Number)priceField.getValue()).doubleValue();

        SpinnerModel dateModel = dateSpinner.getModel();
        Date date = ((SpinnerDateModel)dateModel).getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        String categoryString = (String) categoryBox.getSelectedItem();
        PurchaseCategory purchaseCategory = PurchaseCategory.valueOf(categoryString);

        Purchase newPurchase = new Purchase(name, price, day, month, year, purchaseCategory);

        purchaseLog.addPurchaseByMonth(newPurchase);
        purchaseLog.addPurchaseByCategory(newPurchase, purchaseCategory);
        purchaseLog.addPurchaseToHistory(newPurchase);
    }
}
