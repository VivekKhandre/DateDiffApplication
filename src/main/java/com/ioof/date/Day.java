package com.ioof.date;

/**
 * Created by jan on 22/10/17.
 * <p>
 * This class will maintain the day
 */
public class Day {

    private int day;

    public Day(int day) {
        this.day = day;
    }

    public Day(String day) {
        this(Integer.parseInt(day));
    }

    public int getValue() {
        return day;
    }
}
