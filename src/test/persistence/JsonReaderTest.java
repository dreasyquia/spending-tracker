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

// CITATION: based on JsonReaderTest in JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PurchaseLog purchaseLog = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPurchaseLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPurchaseLog.json");
        try {
            PurchaseLog purchaseLog = reader.read();
            List<Purchase> purchaseHistory = purchaseLog.getPurchaseHistory();
            Map<String, List<Purchase>> monthMap = purchaseLog.getMonthMap();
            Map<PurchaseCategory, List<Purchase>> categoryMap = purchaseLog.getCategoryMap();

            assertEquals(0, purchaseHistory.size());
            assertEquals(0, monthMap.size());
            assertEquals(0, categoryMap.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPurchaseLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPurchaseLog.json");
        try {
            PurchaseLog purchaseLog = reader.read();
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
            fail("Couldn't read from file");
        }
    }
}