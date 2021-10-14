package model;

// Represents a purchase having a product/service name, price, and date, month, and year of purchase
public class Purchase {
    private String name;
    private double price;
    private Date purchaseDate;
    private PurchaseCategory category;

    // REQUIRES: price >= 0.0; date in [1,31]; month in [1,12]; year > 0
    // EFFECTS: creates a purchase with given product/service name, given price, and given date of purchase;
    //          category is initialized as Null
    public Purchase(String productOrServiceName, double price, int date, int month, int year) {

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

}
