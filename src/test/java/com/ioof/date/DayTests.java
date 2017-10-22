package com.ioof.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class DayTests {

    @TestConfiguration
    static class DayTestContextConfiguration {
        @Bean
        public Day day() {
            return new Day(1);
        }

        @Bean
        public Day day1() {
            return new Day("31");
        }
    }

    @Autowired
    private Day day, day1;

    @Test
    public void shouldExpectOneValueOfDay() {
        int expectedValue = 1;
        assertEquals(expectedValue, day.getValue());
    }

    @Test
    public void shouldExpectTwentyOneValueOfDay() {
        int expectedValue = 31;
        assertEquals(expectedValue, day1.getValue());
    }
}
