package model;

import java.util.*;

public class PurchaseLog {
    private Map<String, List<Purchase>> monthMap;
    private Map<PurchaseCategory, List<Purchase>> categoryMap;


    public PurchaseLog() {
        monthMap = new HashMap<>();
        categoryMap = new EnumMap<>(PurchaseCategory.class);
    }

    public Map<String, List<Purchase>> getMonthMap() {
        return monthMap;
    }

    public Map<PurchaseCategory, List<Purchase>> getCategoryMap() {
        return categoryMap;
    }

    // MODIFIES: this
    // EFFECTS: adds purchase to list of purchases in corresponding key if key already exists - keys represent
    //          months of specific years; otherwise creates a new key with a list containing purchase
    public void addPurchaseByMonth(Purchase purchase) {
        Date purchaseDate = purchase.getPurchaseDate();
        String key = purchaseDate.dateToKey();
        List<Purchase> purchaseList = new ArrayList<>();
        monthMap.putIfAbsent(key, purchaseList);
        purchaseList = monthMap.get(key);
        purchaseList.add(purchase);
    }

    // MODIFIES: this
    // EFFECTS: adds purchase to list of purchases in corresponding key if key already exists - keys represent
    //          different kinds of purchases; otherwise creates a new key with a list containing purchase
    public void addPurchaseByCategory(Purchase purchase, PurchaseCategory category) {
        List<Purchase> purchaseList = new ArrayList<>();
        categoryMap.putIfAbsent(category, purchaseList);
        purchaseList = categoryMap.get(category);
        purchaseList.add(purchase);
    }

    // REQUIRES: month in [1,12]; year > 0
    // EFFECTS: returns the total cost of all purchases made in the given month of the given year if corresponding
    //          key in monthMap exists; otherwise returns 0
    public double calculateMoneySpentInMonth(int month, int year) {
        String monthString = Integer.toString(month);
        String yearString = Integer.toString(year);
        String key = monthString.concat(yearString);
        double totalCost = 0;

        if (monthMap.containsKey(key)) {
            List<Purchase> purchaseList = monthMap.get(key);

            for (Purchase p : purchaseList) {
                totalCost = totalCost + p.getPrice();
            }
        }

        return totalCost;
    }

}