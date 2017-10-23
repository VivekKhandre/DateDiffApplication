package com.ioof.date;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vivek on 22/10/17.
 * <p>
 * IoofDate: Validate entered string as DD MM YYYY and create date by extracting Day, Month & Year from string.
 */

public class IoofDate {
    private static Pattern DD_MM_YYYY_PATTERN = Pattern.compile("^(3[01]|[12][0-9]|0[1-9]) (1[0-2]|0[1-9]) (19\\d\\d|20\\d\\d)");
    private Day day;
    private Month month;
    private Year year;
    private Boolean valid = false;

    public IoofDate(String date) {
        extractDateComponents(date);
    }

    private void extractDateComponents(String date) {
        Matcher dateMatcher = DD_MM_YYYY_PATTERN.matcher(date);
        if (dateMatcher.matches()) {
            day = new Day(dateMatcher.group(1));
            month = new Month(dateMatcher.group(2));
            year = new Year(dateMatcher.group(3));
            valid = true;
        }
    }

    /**
     * Returns the day
     *
     * @return
     */
    public Day getDay() {
        return day;
    }

    /**
     * Returns the month
     *
     * @return
     */
    public Month getMonth() {
        return month;
    }

    /**
     * Returns the year
     *
     * @return
     */
    public Year getYear() {
        return year;
    }

    /**
     * Check if entered dateString is valid format and valid day against the months. For example, 31 April is invalid, 29 Feb is Valid for Leap Year
     *
     * @return
     */
    public Boolean isValid() {
        return valid && getDay().getValue() <= getMonth().daysCount(getYear().isLeap());
    }

    /**
     * This will return totol number of days till date. It will calculate based on days till previous + day till previous month + day
     * @return
     */
    public Long getTotalDays() {
        return isValid() ? getYear().daysBeforeYear() + getMonth().daysTillMonth(getYear().isLeap()) + getDay().getValue() : 0l;
    }

    /**
     * calculate the days difference between earlies and latest date.
     * @param earliestDate
     * @return
     */
    public Long difference(IoofDate earliestDate) {
        return this.getTotalDays() - earliestDate.getTotalDays();
    }
}
