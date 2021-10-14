package model;

import java.util.*;

public class PurchaseLog {
    private Map<Integer, List<Purchase>> monthMap;
    private Map<PurchaseCategory, List<Purchase>> categoryMap;


    public PurchaseLog() {
        monthMap = new HashMap<>();
        categoryMap = new EnumMap<>(PurchaseCategory.class);
    }

    public Map<Integer, List<Purchase>> getMonthMap() {
        return monthMap;
    }

    public Map<PurchaseCategory, List<Purchase>> getCategoryMap() {
        return categoryMap;
    }

    // MODIFIES: this
    // EFFECTS: adds purchase to list of purchases in corresponding key if key already exists - keys represent
    //          months of specific years; otherwise creates a new key with a list containing purchase
    public void addPurchaseByMonth(Purchase purchase) {

    }

    // MODIFIES: this
    // EFFECTS: adds purchase to list of purchases in corresponding key if key already exists - keys represent
    //          different kinds of purchases; otherwise creates a new key with a list containing purchase
    public void addPurchaseByCategory(Purchase purchase, PurchaseCategory category) {
// putifabsent
    }

    // REQUIRES: month in [1,12]; year > 0
    // EFFECTS: returns the total cost of all purchases made in the given month of the given year
    public int calculateMoneySpentInMonth(int month, int year) {
        return 0;
    }

}
