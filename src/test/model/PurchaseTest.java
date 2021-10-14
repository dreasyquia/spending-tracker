package model;

import model.exceptions.*;
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
        try {
            testPurchase = new Purchase("A", 1.00, 26,6,2021);
        } catch (EmptyNameException e) {
            e.printStackTrace();
        } catch (NegativePriceException e) {
            e.printStackTrace();
        } catch (InvalidDayException e) {
            e.printStackTrace();
        } catch (InvalidMonthException e) {
            e.printStackTrace();
        } catch (InvalidYearException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConstructor() {
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
    void testSetCategory() {
        testPurchase.setCategory(PurchaseCategory.Miscellaneous);
    }
}
