package persistence;

import model.Date;
import model.Purchase;
import model.PurchaseCategory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPurchase(String name, double price, Date date, PurchaseCategory category, Purchase purchase) {
        Date purchaseDate = purchase.getPurchaseDate();

        assertEquals(name, purchase.getName());
        assertEquals(price, purchase.getPrice());
        assertEquals(date.getDay(), purchaseDate.getDay());
        assertEquals(date.getMonth(), purchaseDate.getMonth());
        assertEquals(date.getYear(), purchaseDate.getYear());
        assertEquals(category, purchase.getCategory());
    }
}
