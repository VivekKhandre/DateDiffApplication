package com.ioof.date;

/**
 * Created by jan on 22/10/17.
 */
public class Year {

    private int year;
    private int yearFrom1900;

    public Year(int year) {
        this.year = year;
        this.yearFrom1900 = (year % 1900);

    }

    public Year(String year) {
        this(Integer.parseInt(year));
    }

    public int getValue() {
        return year;
    }

    public Long daysBeforeYear() {
        return (this.yearFrom1900 * 365l) + (this.yearFrom1900 - 1) / 4;
    }

    public boolean isLeap() {
        return !(year % 4 != 0 || (year % 100 == 0 && year % 400 != 0));
    }
}
