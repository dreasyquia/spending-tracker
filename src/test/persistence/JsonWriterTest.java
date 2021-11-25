package persistence;

import model.Date;
import model.Purchase;
import model.PurchaseCategory;
import model.PurchaseLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// CITATION: based on JsonWriterTest in JsonSerializationDemo
//           (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            PurchaseLog purchaseLog = new PurchaseLog();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPurchaseLog() {
        try {
            PurchaseLog purchaseLog = new PurchaseLog();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPurchaseLog.json");
            writer.open();
            writer.write(purchaseLog);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPurchaseLog.json");
            purchaseLog = reader.read();
            List<Purchase> purchaseHistory = purchaseLog.getPurchaseHistory();
            Map<String, List<Purchase>> monthMap = purchaseLog.getMonthMap();
            Map<PurchaseCategory, List<Purchase>> categoryMap = purchaseLog.getCategoryMap();

            assertEquals(0, purchaseHistory.size());
            assertEquals(0, monthMap.size());
            assertEquals(0, categoryMap.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            PurchaseLog purchaseLog = new PurchaseLog();
            Purchase dress = new Purchase("Dress", 21.45, 15, 10, 2021);
            purchaseLog.addPurchaseToHistory(dress);
            purchaseLog.addPurchaseByMonth(dress);
            purchaseLog.addPurchaseByCategory(dress, PurchaseCategory.Personal);
            dress.setCategory(PurchaseCategory.Personal);

            Purchase shirt = new Purchase("Shirt", 7, 11, 10, 2021);
            purchaseLog.addPurchaseToHistory(shirt);
            purchaseLog.addPurchaseByMonth(shirt);

            Purchase pizza = new Purchase("Pizza", 4.50, 26, 6, 2021);
            purchaseLog.addPurchaseToHistory(pizza);
            purchaseLog.addPurchaseByMonth(pizza);
            purchaseLog.addPurchaseByCategory(pizza, PurchaseCategory.Food);
            pizza.setCategory(PurchaseCategory.Food);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPurchaseLog.json");
            writer.open();
            writer.write(purchaseLog);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPurchaseLog.json");
            purchaseLog = reader.read();
            List<Purchase> purchaseHistory = purchaseLog.getPurchaseHistory();
            Map<String, List<Purchase>> monthMap = purchaseLog.getMonthMap();
            List<Purchase> junePurchases = monthMap.get("62021");
            List<Purchase> octoberPurchases = monthMap.get("102021");
            Map<PurchaseCategory, List<Purchase>> categoryMap = purchaseLog.getCategoryMap();
            List<Purchase> personalPurchases = categoryMap.get(PurchaseCategory.Personal);
            List<Purchase> foodPurchases = categoryMap.get(PurchaseCategory.Food);

            assertEquals(3, purchaseHistory.size());
            assertEquals(2, monthMap.size());
            assertEquals(2, categoryMap.size());
            checkPurchase("Dress",21.45, new Date(15,10,2021),
                    PurchaseCategory.Personal, purchaseHistory.get(0));
            checkPurchase("Shirt",7, new Date(11,10,2021),
                    PurchaseCategory.Null, purchaseHistory.get(1));
            checkPurchase("Pizza",4.50, new Date(26,6,2021),
                    PurchaseCategory.Food, purchaseHistory.get(2));

            checkPurchase("Pizza",4.50, new Date(26,6,2021),
                    PurchaseCategory.Food, junePurchases.get(0));
            checkPurchase("Dress",21.45, new Date(15,10,2021),
                    PurchaseCategory.Personal, octoberPurchases.get(0));
            checkPurchase("Shirt",7, new Date(11,10,2021),
                    PurchaseCategory.Null, octoberPurchases.get(1));

            checkPurchase("Dress",21.45, new Date(15,10,2021),
                    PurchaseCategory.Personal, personalPurchases.get(0));
            checkPurchase("Pizza",4.50, new Date(26,6,2021),
                    PurchaseCategory.Food, foodPurchases.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
