package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a specific date with the day, the month, and the year
public class Date implements Writable {
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

    // EFFECTS: returns a key composed of month and year of Date
    public String dateToKey() {
        String monthString = Integer.toString(month);
        String yearString = Integer.toString(year);
        return monthString.concat(yearString);
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Day", day);
        jsonObject.put("Month", month);
        jsonObject.put("Year", year);

        return jsonObject;
    }
}
