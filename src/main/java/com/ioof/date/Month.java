package com.ioof.date;

/**
 * Created by jan on 22/10/17.
 *
 * This will store month and calculates the days till previous month.
 */
public class Month {

    private int month;

    public Month(int month) {
        this.month = month;
    }

    public Month(String month) {
        this(Integer.parseInt(month));
    }

    public int getValue() {
        return month;
    }

    /**
     * This will return the days till previous month based on leap year.
     *
     * @param leapYear
     * @return
     */
    public int daysTillMonth(boolean leapYear) {
        int leap = leapYear ? 1 : 0;
        switch (month) {
            case 1:
                return 0;
            case 2:
                return 31;
            case 3:
                return 59 + leap;
            case 4:
                return 90 + leap;
            case 5:
                return 120 + leap;
            case 6:
                return 151 + leap;
            case 7:
                return 181 + leap;
            case 8:
                return 212 + leap;
            case 9:
                return 243 + leap;
            case 10:
                return 273 + leap;
            case 11:
                return 304 + leap;
            case 12:
                return 334 + leap;
            default:
                return 334 + leap;
        }
    }


    /**
     * This will return days count for respective months either 28, 30 or 31.
     *
     * @param leapYear
     * @return
     */
    public int daysCount(boolean leapYear) {
        switch (month) {
            case 2:
                return (leapYear ? 29 : 28);
            case 4:  //APRIL
            case 6:  //JUNE
            case 9:  //SEPTEMBER
            case 11: //NOVEMBER
                return 30;
            default:
                return 31;
        }
    }
}
