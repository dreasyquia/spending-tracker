package model;

public enum PurchaseCategory {
    Housing, Transportation, Food, Utilities, Insurance, Healthcare,
    Personal, Lifestyle, Entertainment, Miscellaneous, Null;

    // REQUIRES: given string matches one of PurchaseCategory
    // EFFECTS: converts given string to corresponding PurchaseCategory
    public PurchaseCategory switchCategory(String category) {
        if (category.equals("Housing")) {
            return Housing;
        } else if (category.equals("Transportation")) {
            return Transportation;
        } else if (category.equals("Food")) {
            return Food;
        } else if (category.equals("Utilities")) {
            return Utilities;
        } else if (category.equals("Insurance")) {
            return Insurance;
        } else if (category.equals("Healthcare")) {
            return Healthcare;
        } else if (category.equals("Personal")) {
            return Personal;
        } else if (category.equals("Lifestyle")) {
            return Lifestyle;
        } else if (category.equals("Entertainment")) {
            return Entertainment;
        } else {
            return Miscellaneous;
        }
    }
}