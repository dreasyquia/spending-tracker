package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseTest {
    private Purchase testPurchase;

    @BeforeEach
    void runBefore() {
            testPurchase = new Purchase("A", 1.00, 26,6,2021);
    }

    @Test
    void testConstructorWithNullCategory() {
        String name = testPurchase.getName();
        double price = testPurchase.getPrice();
        Date purchaseDate = testPurchase.getPurchaseDate();
        int day = purchaseDate.getDay();
        int month = purchaseDate.getMonth();
        int year = purchaseDate.getYear();
        PurchaseCategory category = testPurchase.getCategory();

        assertEquals("A", name);
        assertEquals(1.00, price);
        assertEquals(26, day);
        assertEquals(6, month);
        assertEquals(2021, year);
        assertEquals(PurchaseCategory.Null, category);
    }

    @Test
    void testConstructorWithGivenCategory() {
        Purchase testPurchase = new Purchase("A", 1.00, 26,6,2021,
                PurchaseCategory.Personal);

        String name = testPurchase.getName();
        double price = testPurchase.getPrice();
        Date purchaseDate = testPurchase.getPurchaseDate();
        int day = purchaseDate.getDay();
        int month = purchaseDate.getMonth();
        int year = purchaseDate.getYear();
        PurchaseCategory category = testPurchase.getCategory();

        assertEquals("A", name);
        assertEquals(1.00, price);
        assertEquals(26, day);
        assertEquals(6, month);
        assertEquals(2021, year);
        assertEquals(PurchaseCategory.Personal, category);
    }

    @Test
    void testSetCategory() {
        testPurchase.setCategory(PurchaseCategory.Miscellaneous);
        assertEquals(PurchaseCategory.Miscellaneous, testPurchase.getCategory());
    }

    @Test
    void testToJson() {
        JSONObject testJsonPurchase = testPurchase.toJson();
        JSONObject jsonPurchaseDate = testJsonPurchase.getJSONObject("Purchase Date");
        Date purchaseDate = testPurchase.getPurchaseDate();
        int day = purchaseDate.getDay();
        int month = purchaseDate.getMonth();
        int year = purchaseDate.getYear();

        assertEquals("A", testJsonPurchase.getString("Name"));
        assertEquals(1.00, testJsonPurchase.getDouble("Price"));
        assertEquals(26, jsonPurchaseDate.getInt("Day"));
        assertEquals(6, jsonPurchaseDate.getInt("Month"));
        assertEquals(2021, jsonPurchaseDate.getInt("Year"));
        assertEquals(PurchaseCategory.Null, testJsonPurchase.get("Category"));
    }
}
