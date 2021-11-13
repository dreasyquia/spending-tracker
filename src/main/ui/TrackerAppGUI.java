package ui;

import model.PurchaseLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;

public class TrackerAppGUI extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String JSON_STORE = "./data/purchaseLog.json";

    protected PurchaseLog purchaseLog;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public TrackerAppGUI() {
        super("Spending Tracker");
        initializeFields();
        initializeGraphics();
    }

    public PurchaseLog getPurchaseLog() {
        return purchaseLog;
    }

    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }

    public JsonReader getJsonReader() {
        return jsonReader;
    }

    public void setPurchaseLog(PurchaseLog purchaseLog) {
        this.purchaseLog = purchaseLog;
    }

    private void initializeFields() {
        purchaseLog = new PurchaseLog();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        initializeMainMenu();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeMainMenu() {
        ImageIcon icon = new ImageIcon("data/spendingTrackerIcon.png");
        JLabel iconLabel = new JLabel(icon);

        add(iconLabel, BorderLayout.CENTER);

        JPanel mainMenu = new JPanel();

        mainMenu.setLayout(new GridLayout(3,2));
        add(mainMenu, BorderLayout.SOUTH);

        new CreatePurchaseButton(this, mainMenu);
        new ViewPurchasesButton(this, mainMenu);
        new CalculateButton(this, mainMenu);
        new SaveLogButton(this, mainMenu);
        new LoadLogButton(this, mainMenu);
        new QuitButton(this, mainMenu);

    }

    public static void main(String[] args) {
        new TrackerAppGUI();
    }
}
