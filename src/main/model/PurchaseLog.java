package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a purchase log with a list of all recorded purchases, a map of purchases grouped by their month of
// purchase, and a map of purchases grouped by their category
public class PurchaseLog implements Writable {
    private List<Purchase> purchaseHistory;
    private Map<String, List<Purchase>> monthMap;
    private Map<PurchaseCategory, List<Purchase>> categoryMap;

    // EFFECTS: constructs a purchase log with a purchaseHistory, monthMap, and categoryMap
    public PurchaseLog() {
        purchaseHistory = new ArrayList<>();
        monthMap = new HashMap<>();
        categoryMap = new EnumMap<>(PurchaseCategory.class);
    }

    public List<Purchase> getPurchaseHistory() {
        return purchaseHistory;
    }

    public Map<String, List<Purchase>> getMonthMap() {
        return monthMap;
    }

    public Map<PurchaseCategory, List<Purchase>> getCategoryMap() {
        return categoryMap;
    }

    public void setMonthMap(Map<String, List<Purchase>> monthMap) {
        this.monthMap = monthMap;
    }

    public void setCategoryMap(Map<PurchaseCategory, List<Purchase>> categoryMap) {
        this.categoryMap = categoryMap;
    }

    // MODIFIES: this
    // EFFECTS: adds purchase to purchaseHistory
    public void addPurchaseToHistory(Purchase purchase) {
        purchaseHistory.add(purchase);
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Purchase History", purchaseHistoryToJson());
        json.put("Month Map", monthMapToJson());
        json.put("Category Map", categoryMapToJson());
        return json;
    }

    // EFFECTS: returns purchaseHistory as a JSON array
    private JSONArray purchaseHistoryToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Purchase p: purchaseHistory) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns monthMap as a JSON object
    public JSONObject monthMapToJson() {
        JSONObject jsonMonthMap = new JSONObject();

        for (String key: monthMap.keySet()) {
            List<Purchase> purchaseList = monthMap.get(key);
            JSONArray jsonArray = new JSONArray();

            for (Purchase p: purchaseList) {
                jsonArray.put(p.toJson());
            }

            jsonMonthMap.put(key, jsonArray);
        }

        return jsonMonthMap;
    }

    // EFFECTS: returns monthMap as a JSON object
    private JSONObject categoryMapToJson() {
        JSONObject jsonCategoryMap = new JSONObject();

        for (PurchaseCategory key: categoryMap.keySet()) {
            List<Purchase> purchaseList = categoryMap.get(key);
            JSONArray jsonArray = new JSONArray();

            for (Purchase p: purchaseList) {
                jsonArray.put(p.toJson());
            }

            jsonCategoryMap.put(key.toString(), jsonArray);
        }

        return jsonCategoryMap;
    }

}
