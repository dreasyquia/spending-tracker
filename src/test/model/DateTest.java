package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {
    private Date testDate;

    @BeforeEach
    void runBefore() {
        testDate = new Date(26, 6, 2021);
    }

    @Test
    void testConstructor() {
        int day = testDate.getDay();
        int month = testDate.getMonth();
        int year = testDate.getYear();

        assertEquals(26, day);
        assertEquals(6, month);
        assertEquals(2021, year);
    }

    @Test
    void testDateToKey() {
        int testKey = testDate.dateToKey();

        assertEquals(062021, testKey);
    }
}
