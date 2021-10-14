package model;

// Represents a specific date with the day, the month, and the year
public class Date {
    private int day;
    private int month;
    private int year;

    // EFFECTS: day of date is set to givenDay; month of date is set to
    //          givenMonth; year of date is set to givenYear
    public Date(int givenDay, int givenMonth, int givenYear) {
        this.day = givenDay;
        this.month = givenMonth;
        this.year = givenYear;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    // EFFECTS: returns a key composed of given month and year
    public int dateToKey() {
        return 0;
    }
}
