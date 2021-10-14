package ui;

import model.Purchase;
import model.PurchaseLog;

import java.util.Scanner;

// Spending tracker application
public class TrackerApp {
    private PurchaseLog purchaseLog;
    private Scanner userInput;

    // EFFECTS: runs the spending tracker application
    public TrackerApp() {
        runTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTracker() {
        boolean continueRunning = true;
        String userCommand = null;

        initialize();

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

    // MODIFIES: this
    // EFFECTS: initializes purchase log
    private void initialize() {
        purchaseLog = new PurchaseLog();
        userInput = new Scanner(System.in);
        userInput.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options available to user
    private void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("\tN - Create a new purchase.");
        System.out.println("\tC - Categorize a purchase.");
        System.out.println("\tV - View all purchases made.");
        System.out.println("\tM - Calculate how much money you spent in a given month.");
        System.out.println("\tQ - Quit application.");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("N")) {
            createPurchase();
        } else if (command.equals("C")) {
            categorizePurchase();
        } else if (command.equals("V")) {
            viewAllPurchases();
        } else if (command.equals("M")) {
            doCalculationOfMoneySpentInMonth();
        } else if (command.equals("Q")) {
            System.out.println("\nClosing spending tracker...");
        } else {
            System.out.println("Invalid input. Please select one of the following options:");
        }
    }

    private void createPurchase() {
        System.out.println("\nEnter the name of the product or service:");
        String productOrServiceName = userInput.next();
        System.out.println("Enter the price: $");
        double price = userInput.nextDouble();
        System.out.println("\nUsing numbers from 1 to 31, enter the day of purchase:");
        int day = userInput.nextInt();
        System.out.println("\nUsing numbers from 1 to 12, enter the month of purchase:");
        int month = userInput.nextInt();
        System.out.println("\nEnter the year of purchase:");
        int year = userInput.nextInt();

        Purchase newPurchase = new Purchase(productOrServiceName, price, day, month, year);

        purchaseLog.addPurchaseByMonth(newPurchase);
    }

    private void categorizePurchase() {
        System.out.println("");
    }

    private void viewAllPurchases() {}

    private void doCalculationOfMoneySpentInMonth() {
        System.out.println("\nUsing numbers from 1 to 12, enter the month you would like to view:");
        int month = userInput.nextInt();
        System.out.println("\nEnter the year of the month you would like to view:");
        int year = userInput.nextInt();

        purchaseLog.calculateMoneySpentInMonth(month, year);
    }

}
