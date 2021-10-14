package model;

import com.sun.media.sound.InvalidDataException;
import model.exceptions.*;

// Represents a purchase having a product/service name, price, purchase date, and category
public class Purchase {
    private String name;
    private double price;
    private Date purchaseDate;
    private PurchaseCategory category;

    // REQUIRES: price >= 0.0; day in [1,31]; month in [1,12]; year > 0
    // EFFECTS: creates a purchase with given product/service name, given price, and given date of purchase;
    //          category is initialized as Null;
    //          throws EmptyNameException if productOrServiceName has zero length
    //          throws NegativePriceException if price < 0
    //          throws InvalidDayException if day is not in the range [1,31]
    //          throws InvalidMonthException if month is not in the range [1,12]
    //          throws InvalidYearException if year <= 0
    public Purchase(String productOrServiceName, double price, int day, int month, int year) throws EmptyNameException,
            NegativePriceException, InvalidDayException, InvalidMonthException, InvalidYearException {
        if (productOrServiceName.isEmpty()) {
            throw new EmptyNameException();
        }
        if (price < 0) {
            throw new NegativePriceException();
        }
        if (day < 1 || day > 31) {
            throw new InvalidDayException();
        }
        if (month < 1 || month > 12) {
            throw new InvalidMonthException();
        }
        if (year <= 0) {
            throw new InvalidYearException();
        }
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

}
