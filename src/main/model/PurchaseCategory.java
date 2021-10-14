package model;

import model.exceptions.InvalidCategoryException;

public enum PurchaseCategory {
    Housing, Transportation, Food, Utilities, Insurance, Healthcare,
    Personal, Lifestyle, Entertainment, Miscellaneous, Null;

    // EFFECTS: converts given categoryString to corresponding PurchaseCategory
    //          throws InvalidCategoryException if categoryString does not match any PurchaseCategory
    public PurchaseCategory switchCategory(String categoryString) throws InvalidCategoryException {
        if (categoryString.equals("Housing")) {
            return Housing;
        } else if (categoryString.equals("Transportation")) {
            return Transportation;
        } else if (categoryString.equals("Food")) {
            return Food;
        } else if (categoryString.equals("Utilities")) {
            return Utilities;
        } else if (categoryString.equals("Insurance")) {
            return Insurance;
        } else if (categoryString.equals("Healthcare")) {
            return Healthcare;
        } else if (categoryString.equals("Personal")) {
            return Personal;
        } else if (categoryString.equals("Lifestyle")) {
            return Lifestyle;
        } else if (categoryString.equals("Entertainment")) {
            return Entertainment;
        } else if (categoryString.equals("Miscellaneous")) {
            return Miscellaneous;
        } else {
            throw new InvalidCategoryException();
        }
    }
}