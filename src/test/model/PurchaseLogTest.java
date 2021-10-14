package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PurchaseLogTest {
    private PurchaseLog testPurchaseLog;
    private Map<String, List<Purchase>> testMonthMap;
    private Map<PurchaseCategory, List<Purchase>> testCategoryMap;


    @BeforeEach
    void runBefore() {
        testPurchaseLog = new PurchaseLog();
        testMonthMap = testPurchaseLog.getMonthMap();
        testCategoryMap = testPurchaseLog.getCategoryMap();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testMonthMap.size());
        assertEquals(0, testCategoryMap.size());
    }

    @Test
    void testAddPurchaseByMonthNewMonth() {
        Purchase testPurchase = new Purchase("A", 1.00, 26,6,2021);
        testPurchaseLog.addPurchaseByMonth(testPurchase);
        List<Purchase> testPurchaseList = testMonthMap.get("62021");

        assertTrue(testPurchaseList.contains(testPurchase));
        assertEquals(1, testPurchaseList.size());
    }

    @Test
    void testAddPurchaseByMonthExistingMonth() {
        Purchase testPurchaseA = new Purchase("A", 1.00, 26,6,2021);
        testPurchaseLog.addPurchaseByMonth(testPurchaseA);
        List<Purchase> testPurchaseList = testMonthMap.get("62021");

        Purchase testPurchaseB = new Purchase("B", 1.00, 26,6,2021);
        testPurchaseLog.addPurchaseByMonth(testPurchaseB);
        Purchase testPurchaseBInLog = testPurchaseList.get(1);

        assertTrue(testPurchaseList.contains(testPurchaseB));
        assertEquals(2, testPurchaseList.size());
        assertEquals("B", testPurchaseBInLog.getName());
    }

    @Test
    void testAddPurchaseByCategoryNewCategory() {
        Purchase testPurchase = new Purchase("A", 1.00, 26,6,2021);
        testPurchaseLog.addPurchaseByCategory(testPurchase, PurchaseCategory.Miscellaneous);
        List<Purchase> testPurchaseList = testCategoryMap.get(PurchaseCategory.Miscellaneous);

        assertTrue(testPurchaseList.contains(testPurchase));
        assertEquals(1, testPurchaseList.size());
    }

    @Test
    void testAddPurchaseByCategoryExistingCategory() {
        Purchase testPurchaseA = new Purchase("A", 1.00, 26,6,2021);
        testPurchaseLog.addPurchaseByCategory(testPurchaseA, PurchaseCategory.Miscellaneous);
        List<Purchase> testPurchaseList = testCategoryMap.get(PurchaseCategory.Miscellaneous);

        Purchase testPurchaseB = new Purchase("B", 1.00, 26,6,2021);
        testPurchaseLog.addPurchaseByCategory(testPurchaseB, PurchaseCategory.Miscellaneous);
        Purchase testPurchaseBInLog = testPurchaseList.get(1);

        assertTrue(testPurchaseList.contains(testPurchaseB));
        assertEquals(2, testPurchaseList.size());
        assertEquals("B", testPurchaseBInLog.getName());
    }

    @Test
    void testCalculateMoneySpentInMonthNoPurchases() {
        double totalCost = testPurchaseLog.calculateMoneySpentInMonth(6,2021);

        assertEquals(0.00, totalCost);
    }

    @Test
    void testCalculateMoneySpentInMonthOnePurchases() {
        Purchase testPurchase = new Purchase("A", 1.00, 26,6,2021);
        testPurchaseLog.addPurchaseByMonth(testPurchase);
        double totalCost = testPurchaseLog.calculateMoneySpentInMonth(6,2021);

        assertEquals(1.00, totalCost);
    }

    @Test
    void testCalculateMoneySpentInMonthSeveralPurchases() {
        Purchase testPurchaseA = new Purchase("A", 1.00, 26,6,2021);
        Purchase testPurchaseB = new Purchase("B", 1.75, 26,6,2021);
        Purchase testPurchaseC = new Purchase("C", 0.25, 26,6,2021);
        Purchase testPurchaseD = new Purchase("D", 5.00, 26,6,2021);
        Purchase testPurchaseE = new Purchase("E", 10.10, 26,6,2021);
        testPurchaseLog.addPurchaseByMonth(testPurchaseA);
        testPurchaseLog.addPurchaseByMonth(testPurchaseB);
        testPurchaseLog.addPurchaseByMonth(testPurchaseC);
        testPurchaseLog.addPurchaseByMonth(testPurchaseD);
        testPurchaseLog.addPurchaseByMonth(testPurchaseE);
        double totalCost = testPurchaseLog.calculateMoneySpentInMonth(6,2021);

        assertEquals(18.10, totalCost);
    }
}