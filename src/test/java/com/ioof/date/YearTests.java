package com.ioof.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class YearTests {

    @TestConfiguration
    static class YearTestContextConfiguration {
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
    private Year year;

    @Autowired
    private Year leapYear;

    @Test
    public void shouldExpect2001ValueOfYear()  {
        int expectedValue = 2001;
        assertEquals(expectedValue, year.getValue());
    }

    @Test
    public void shouldExpect36890DaysBeforeYear()  {
        Long expectedValue = 36890l;
        assertEquals(expectedValue, year.daysBeforeYear());
    }

    @Test
    public void shouldExpectIsLeapYearFalse()  {
        assertEquals(false, year.isLeap());
    }

    @Test
    public void shouldExpectIsLeapYearTrue()  {
        assertEquals(true, leapYear.isLeap());
    }

    @Test
    public void shouldExpectTotalDaysBeforeYear()  {
        Long expected = 36524l;
        assertEquals(expected, leapYear.daysBeforeYear());
    }
}
