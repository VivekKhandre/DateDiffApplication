package com.ioof.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MonthTests {

    @TestConfiguration
    static class MonthTestContextConfiguration {
        @Bean
        public Month jan() {
            return new Month(1);
        }

        @Bean
        public Month march() {
            return new Month("3");
        }

        @Bean
        public Year year() {
            return new Year(2001);
        }

        @Bean
        public Year leapYear() {
            return new Year("2000");
        }
    }

    @Autowired
    private Month jan, march;

    @Autowired
    private Year year, leapYear;

    @Test
    public void shouldExpectOneValueOfMonth() {
        int expectedValue = 1;
        assertEquals(expectedValue, jan.getValue());
    }

    @Test
    public void shouldExpectDaysTillMonthForNonLeapYear() {
        int expectedValue = 59;
        assertEquals(expectedValue, march.daysTillMonth(year.isLeap()));
    }

    @Test
    public void shouldExpectDaysTillMonthForLeapYear() {
        int expectedValue = 60;
        assertEquals(expectedValue, march.daysTillMonth(leapYear.isLeap()));
    }
}
