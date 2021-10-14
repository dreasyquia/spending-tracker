package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchaseCategoryTest {
 //Housing, Transportation, Food, Utilities, Insurance, Healthcare,
 //    Personal, Lifestyle, Entertainment, Miscellaneous, Null;

    @Test
    public void testSwitchCategoryToHousing() {
        PurchaseCategory category = PurchaseCategory.Null;

        assertEquals(PurchaseCategory.Housing, category.switchCategory("Housing"));
    }

    @Test
    public void testSwitchCategoryToTransportation() {
        PurchaseCategory category = PurchaseCategory.Null;

        assertEquals(PurchaseCategory.Transportation, category.switchCategory("Transportation"));
    }

    @Test
    public void testSwitchCategoryToFood() {
        PurchaseCategory category = PurchaseCategory.Null;

        assertEquals(PurchaseCategory.Food, category.switchCategory("Food"));
    }

    @Test
    public void testSwitchCategoryToUtilities() {
        PurchaseCategory category = PurchaseCategory.Null;

        assertEquals(PurchaseCategory.Utilities, category.switchCategory("Utilities"));
    }

    @Test
    public void testSwitchCategoryToInsurance() {
        PurchaseCategory category = PurchaseCategory.Null;

        assertEquals(PurchaseCategory.Insurance, category.switchCategory("Insurance"));
    }

    @Test
    public void testSwitchCategoryToHealthcare() {
        PurchaseCategory category = PurchaseCategory.Null;

        assertEquals(PurchaseCategory.Healthcare, category.switchCategory("Healthcare"));
    }

    @Test
    public void testSwitchCategoryToPersonal() {
        PurchaseCategory category = PurchaseCategory.Null;

        assertEquals(PurchaseCategory.Personal, category.switchCategory("Personal"));
    }

    @Test
    public void testSwitchCategoryToLifestyle() {
        PurchaseCategory category = PurchaseCategory.Null;

        assertEquals(PurchaseCategory.Lifestyle, category.switchCategory("Lifestyle"));
    }

    @Test
    public void testSwitchCategoryToEntertainment() {
        PurchaseCategory category = PurchaseCategory.Null;

        assertEquals(PurchaseCategory.Entertainment, category.switchCategory("Entertainment"));
    }

    @Test
    public void testSwitchCategoryToMiscellaneous() {
        PurchaseCategory category = PurchaseCategory.Null;

        assertEquals(PurchaseCategory.Miscellaneous, category.switchCategory("Miscellaneous"));
    }
}
