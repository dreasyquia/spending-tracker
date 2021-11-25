package ui;

import model.Event;
import model.EventLog;
import model.PurchaseLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TrackerAppGUI extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String JSON_STORE = "./data/purchaseLog.json";

    protected PurchaseLog purchaseLog;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private List<Button> buttons;

    // EFFECTS: constructs a tracker app GUI
    public TrackerAppGUI() {
        super("Spending Tracker");
        initializeFields();
        initializeGraphics();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                printLog(EventLog.getInstance());
            }
        });
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

    // MODIFIES: this
    // EFFECTS: initializes the fields of tracker app
    private void initializeFields() {
        purchaseLog = new PurchaseLog();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        buttons = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: initializes the graphics of tracker app
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        initializeMainMenu();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the main menu of tracker app
    private void initializeMainMenu() {
        ImageIcon icon = new ImageIcon("data/spendingTrackerIcon.png");
        JLabel iconLabel = new JLabel(icon);

        add(iconLabel, BorderLayout.CENTER);

        JPanel mainMenu = new JPanel();

        mainMenu.setLayout(new GridLayout(3, 2));
        add(mainMenu, BorderLayout.SOUTH);

        Button createPurchaseButton = new CreatePurchaseButton(this, mainMenu);
        buttons.add(createPurchaseButton);

        Button viewPurchasesButton = new ViewPurchasesButton(this, mainMenu);
        buttons.add(viewPurchasesButton);

        Button calculateButton = new CalculateButton(this, mainMenu);
        buttons.add(calculateButton);

        Button saveLogButton = new SaveLogButton(this, mainMenu);
        buttons.add(saveLogButton);

        Button loadLogButton = new LoadLogButton(this, mainMenu);
        buttons.add(loadLogButton);

        Button quitButton = new QuitButton(this, mainMenu);
        buttons.add(quitButton);

    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.getDescription());
        }
    }

    public static void main(String[] args) {
        new TrackerAppGUI();
    }
}
