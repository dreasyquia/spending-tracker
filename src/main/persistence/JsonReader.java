package persistence;

import model.Date;
import model.Purchase;
import model.PurchaseCategory;
import model.PurchaseLog;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

// CITATION: based on JsonReader in JsonSerializationDemo
//           (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
// Represents a reader that reads purchase log from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads purchase log from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PurchaseLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePurchaseLog(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses purchase log from JSON object and returns it
    private PurchaseLog parsePurchaseLog(JSONObject jsonObject) {
        PurchaseLog purchaseLog = new PurchaseLog();
        addPurchaseHistory(purchaseLog, jsonObject);
        addMonthMap(purchaseLog, jsonObject);
        addCategoryMap(purchaseLog, jsonObject);
        return purchaseLog;
    }

    // MODIFIES: purchaseLog
    // EFFECTS: parses purchaseHistory from JSON object and adds it to purchaseLog
    private void addPurchaseHistory(PurchaseLog purchaseLog, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Purchase History");
        for (Object json : jsonArray) {
            JSONObject nextPurchase = (JSONObject) json;
            addPurchase(purchaseLog, nextPurchase);
        }
    }

    // MODIFIES: purchaseLog
    // EFFECTS: parses purchase from JSON object and adds it to purchaseHistory
    private void addPurchase(PurchaseLog purchaseLog, JSONObject jsonPurchase) {
        Purchase purchase = parsePurchase(jsonPurchase);
        purchaseLog.addPurchaseToHistory(purchase);
    }

    private Purchase parsePurchase(JSONObject jsonPurchase) {
        String name = jsonPurchase.getString("Name");
        double price = jsonPurchase.getDouble("Price");
        Date purchaseDate = parseDate(jsonPurchase.getJSONObject("Purchase Date"));
        int day = purchaseDate.getDay();
        int month = purchaseDate.getMonth();
        int year = purchaseDate.getYear();
        PurchaseCategory category = PurchaseCategory.valueOf(jsonPurchase.getString("Category"));

        Purchase purchase = new Purchase(name, price, day, month, year);
        purchase.setCategory(category);

        return purchase;
    }

    // EFFECTS: parses date from JSON object and returns it
    private Date parseDate(JSONObject jsonDate) {
        int day = jsonDate.getInt("Day");
        int month = jsonDate.getInt("Month");
        int year = jsonDate.getInt("Year");
        return new Date(day, month, year);
    }

    // MODIFIES: purchaseLog
    // EFFECTS: parses monthMap from JSON object and adds it to purchaseLog
    private void addMonthMap(PurchaseLog purchaseLog, JSONObject jsonObject) {
        JSONObject jsonMonthMap = jsonObject.getJSONObject("Month Map");
        Map<String, List<Purchase>> monthMap = new HashMap<>();

        for (String key : jsonMonthMap.keySet()) {
            JSONArray jsonPurchaseList = jsonMonthMap.getJSONArray(key);
            List<Purchase> purchaseList = new ArrayList<>();

            for (Object json : jsonPurchaseList) {
                JSONObject nextPurchase = (JSONObject) json;
                purchaseList.add(parsePurchase(nextPurchase));
            }

            monthMap.put(key, purchaseList);
        }

        purchaseLog.setMonthMap(monthMap);
    }

    // MODIFIES: purchaseLog
    // EFFECTS: parses categoryMap from JSON object and adds it to purchaseLog
    private void addCategoryMap(PurchaseLog purchaseLog, JSONObject jsonObject) {
        JSONObject jsonCategoryMap = jsonObject.getJSONObject("Category Map");
        Map<PurchaseCategory, List<Purchase>> categoryMap = new HashMap<>();

        for (String key : jsonCategoryMap.keySet()) {
            JSONArray jsonPurchaseList = jsonCategoryMap.getJSONArray(key);
            List<Purchase> purchaseList = new ArrayList<>();

            for (Object json : jsonPurchaseList) {
                JSONObject nextPurchase = (JSONObject) json;
                purchaseList.add(parsePurchase(nextPurchase));
            }

            categoryMap.put(PurchaseCategory.valueOf(key), purchaseList);
        }

        purchaseLog.setCategoryMap(categoryMap);
    }
}

