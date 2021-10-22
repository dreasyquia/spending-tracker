package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a purchase having a product/service name, price, purchase date, and category
public class Purchase implements Writable {
    private String name;
    private double price;
    private Date purchaseDate;
    private PurchaseCategory category;

    // REQUIRES: productOrServiceName has non-zero length; price >= 0.0; date in [1,31]; month in [1,12]; year > 0
    // EFFECTS: creates a purchase with given product/service name, given price, and given date of purchase;
    //          category is initialized as Null
    public Purchase(String productOrServiceName, double price, int day, int month, int year) {
        this.name = productOrServiceName;
        this.price = price;
        this.purchaseDate = new Date(day, month, year);
        this.category = PurchaseCategory.Null;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public PurchaseCategory getCategory() {
        return category;
    }

    public void setCategory(PurchaseCategory category) {
        this.category = category;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name", name);
        jsonObject.put("Price", price);
        jsonObject.put("Purchase Date", purchaseDate.toJson());
        jsonObject.put("Category", category);

        return jsonObject;
    }

}
