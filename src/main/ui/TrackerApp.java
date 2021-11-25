package ui;

import model.Date;
import model.Purchase;
import model.PurchaseCategory;
import model.PurchaseLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Spending tracker application
public class TrackerApp {
    private static final String JSON_STORE = "./data/purchaseLog.json";
    private final Map<String, PurchaseCategory> stringToPurchaseCategoryMap = new HashMap<String, PurchaseCategory>() {
        {
            put("Housing", PurchaseCategory.Housing);
            put("Transportation", PurchaseCategory.Transportation);
            put("Food", PurchaseCategory.Food);
            put("Utilities", PurchaseCategory.Utilities);
            put("Insurance", PurchaseCategory.Insurance);
            put("Healthcare", PurchaseCategory.Healthcare);
            put("Personal", PurchaseCategory.Personal);
            put("Lifestyle", PurchaseCategory.Lifestyle);
            put("Entertainment", PurchaseCategory.Entertainment);
            put("Miscellaneous", PurchaseCategory.Miscellaneous);
        }
    };
    private PurchaseLog purchaseLog;
    private Scanner userInput;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs purchase log and runs the spending tracker application
    public TrackerApp() {
        purchaseLog = new PurchaseLog();
        userInput = new Scanner(System.in);
        userInput.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTracker();
    }

    public PurchaseLog getPurchaseLog() {
        return purchaseLog;
    }

    // CITATION: based on runTeller() in TellerApp (https://github.students.cs.ubc.ca/CPSC210/TellerApp.git)
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTracker() {
        boolean continueRunning = true;
        String userCommand;

        while (continueRunning) {
            displayMenu();
            userCommand = userInput.next();
            userCommand = userCommand.toUpperCase();

            if (userCommand.equals("Q")) {
                continueRunning = false;
            } else {
                processCommand(userCommand);
            }
        }

        System.out.println("\nSee you at your next purchase!");
    }

    // EFFECTS: displays menu of options available to user
    private void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("\tN - Create a new purchase.");
        System.out.println("\tV - View all recorded purchases.");
        System.out.println("\tM - Calculate how much money was spent in a given month.");
        System.out.println("\tS - Save purchase log to file.");
        System.out.println("\tL - Load purchase log from file.");
        System.out.println("\tQ - Quit application.");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("N")) {
            createPurchase();
        } else if (command.equals("V")) {
            viewAllPurchases();
        } else if (command.equals("M")) {
            doCalculationOfMoneySpentInMonth();
        } else if (command.equals("S")) {
            savePurchaseLog();
        } else if (command.equals("L")) {
            loadPurchaseLog();
        } else if (command.equals("Q")) {
            System.out.println("\nClosing spending tracker...");
        } else {
            System.out.println("Invalid input. Please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a purchase and adds it to purchaseHistory and monthMap of purchaseLog; gives option to
    //          categorize newly created purchase or return to main menu
    private void createPurchase() {
        String productOrServiceName = getPurchaseName();
        double price = getPurchasePrice();
        int day = getPurchaseDay();
        int month = getPurchaseMonth();
        int year = getPurchaseYear();

        Purchase newPurchase = new Purchase(productOrServiceName, price, day, month, year);
        purchaseLog.addPurchaseByMonth(newPurchase);
        purchaseLog.addPurchaseToHistory(newPurchase);

        System.out.println("\nSelect an option:");
        System.out.println("\tC - Categorize purchase.");
        System.out.println("\tR - Return to main menu.");
        String userCommand = userInput.next();
        userCommand = userCommand.toUpperCase();

        if (userCommand.equals("C")) {
            categorizePurchase(newPurchase);
        }
    }

    // EFFECTS: returns product or service name of purchase given by user
    private String getPurchaseName() {
        System.out.println("\nEnter the name of the product or service:");
        String productOrServiceName = userInput.next();

        while (productOrServiceName.isEmpty()) {
            System.out.println("Name cannot be empty. Please try again.");
            productOrServiceName = userInput.next();
        }
        return productOrServiceName;
    }

    // EFFECTS: returns price of purchase given by user
    private double getPurchasePrice() {
        System.out.print("\nEnter the price: $");
        double price = userInput.nextDouble();

        while (price < 0) {
            System.out.println("Price cannot be negative. Please try again.");
            price = userInput.nextDouble();
        }
        return price;
    }

    // EFFECTS: returns day of purchase given by user
    private int getPurchaseDay() {
        System.out.println("\nUsing integers from 1 to 31, enter the day of purchase:");
        int day = userInput.nextInt();
        while (day < 1 || day > 31) {
            System.out.println("Day must be an integer between 1 and 31. Please try again.");
            day = userInput.nextInt();
        }
        return day;
    }

    // EFFECTS: returns month of purchase given by user
    private int getPurchaseMonth() {
        System.out.println("\nUsing integers from 1 to 12, enter the month of purchase:");
        int month = userInput.nextInt();
        while (month < 1 || month > 12) {
            System.out.println("Month must be an integer between 1 and 12. Please try again.");
            month = userInput.nextInt();
        }
        return month;
    }

    // EFFECTS: returns year of purchase given by user
    private int getPurchaseYear() {
        System.out.println("\nEnter the year of purchase:");
        int year = userInput.nextInt();
        while (year <= 0) {
            System.out.println("Year must be greater than 0. Please try again.");
            year = userInput.nextInt();
        }
        return year;
    }

    // MODIFIES: this
    // EFFECTS: adds purchase to categoryMap of purchaseLog
    private void categorizePurchase(Purchase newPurchase) {
        System.out.println("\nEnter one of the following categories:");
        System.out.println("\tHousing, Transportation, Food, Utilities, Insurance,");
        System.out.println("\tHealthcare, Personal, Lifestyle, Entertainment, Miscellaneous");

        String categoryString = userInput.next();
        String firstCharacter = categoryString.substring(0, 1);
        String remainingCharacters = categoryString.substring(1);
        firstCharacter = firstCharacter.toUpperCase();
        categoryString = firstCharacter.concat(remainingCharacters);

        while (!stringToPurchaseCategoryMap.containsKey(categoryString)) {
            System.out.println("Invalid category selection. Please try again.");
            categoryString = userInput.next();
            firstCharacter = categoryString.substring(0, 1);
            remainingCharacters = categoryString.substring(1);
            firstCharacter = firstCharacter.toUpperCase();
            categoryString = firstCharacter.concat(remainingCharacters);
        }
        PurchaseCategory category = stringToPurchaseCategoryMap.get(categoryString);
        newPurchase.setCategory(category);
        purchaseLog.addPurchaseByCategory(newPurchase, category);
        System.out.println("Successfully added purchase to " + category + ".");
    }

    // EFFECTS: prints all recorded purchases in purchaseHistory of purchaseLog if not empty; otherwise prints
    //          statement that no purchases have been recorded
    private void viewAllPurchases() {
        List<Purchase> purchaseHistory = purchaseLog.getPurchaseHistory();
        if (purchaseHistory.isEmpty()) {
            System.out.println("No purchases have been recorded.");
        } else {
            for (Purchase p : purchaseHistory) {
                Date purchaseDate = p.getPurchaseDate();
                System.out.println("Name: " + p.getName() + " Price: $" + p.getPrice() + " Purchase Date: "
                        + purchaseDate.getDay() + "/" + purchaseDate.getMonth() + "/" + purchaseDate.getYear()
                        + " Category: " + p.getCategory());
            }
        }
    }

    // EFFECTS: calculates the total spending in month that user gives
    private void doCalculationOfMoneySpentInMonth() {
        System.out.println("\nUsing integers from 1 to 12, enter the month you would like to view:");
        int month = userInput.nextInt();
        while (month < 1 || month > 12) {
            System.out.println("Month must be an integer between 1 and 12. Please try again.");
            month = userInput.nextInt();
        }
        System.out.println("\nEnter the year of the month you would like to view:");
        int year = userInput.nextInt();
        while (year <= 0) {
            System.out.println("Year must be greater than 0. Please try again.");
            year = userInput.nextInt();
        }
        double monthlySpending = purchaseLog.calculateMoneySpentInMonth(month, year);
        System.out.printf("Monthly Spending: $%.2f\n", monthlySpending);
    }

    // CITATION: based on saveWorkRoom() in JsonSerializationDemo
    // EFFECTS: saves the purchase log to file
    public void savePurchaseLog() {
        try {
            jsonWriter.open();
            jsonWriter.write(purchaseLog);
            jsonWriter.close();
            System.out.println("Saved purchase log to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // CITATION: based on loadWorkRoom() in JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads purchase log from file
    public void loadPurchaseLog() {
        try {
            purchaseLog = jsonReader.read();
            System.out.println("Loaded purchase log from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
