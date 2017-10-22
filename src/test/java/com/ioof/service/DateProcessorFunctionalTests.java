package com.ioof.service;

import com.ioof.exception.DateFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
public class DateProcessorFunctionalTests {

    @TestConfiguration
    static class DateProcessorTestContextConfiguration {
        @Bean
        public DateProcessor dateProcessor() {
            return new DateProcessor();
        }
    }

    @Autowired
    private DateProcessor dateProcessor;

    @Test(expected = DateFormatException.class)
    public void shouldExpectDateFormatExceptionWhenInvalidEarliestDate() throws Exception {
        dateProcessor.process("11 10, 11 10 2002");
        fail("Expected date format exception");
    }

    @Test(expected = DateFormatException.class)
    public void shouldExpectDateFormatExceptionWhenInvalidLatestDate() throws Exception {
        Long actualResponse = dateProcessor.process("11 10 2000, 11 10 1558");
        fail("Expected date format exception");
    }

    @Test(expected = DateFormatException.class)
    public void shouldExpectDateFormatExceptionWhenNonLeapYear29Feb() throws Exception {
        dateProcessor.process("29 02 1999, 11 10 2001");
        fail("Expected date format exception");
    }

    @Test(expected = DateFormatException.class)
    public void shouldExpectDateFormatExceptionWhenEarliestDateGreaterThanLatest() throws Exception {
        dateProcessor.process("21 02 2003, 11 10 2001");
        fail("Expected date format exception");
    }

    @Test(expected = DateFormatException.class)
    public void shouldExpectDateFormatExceptionWhenCommaMissing() throws Exception {
        dateProcessor.process("21 02 2003 11 10 2001");
        fail("Expected date format exception");
    }

    @Test(expected = DateFormatException.class)
    public void shouldExpectDateFormatExceptionWhenInvalidMonthFormat() throws Exception {
        dateProcessor.process("21 FEB 2003, 11 DEC 2004");
        fail("Expected date format exception");
    }

    @Test(expected = DateFormatException.class)
    public void shouldExpectDateFormatExceptionWhenInvalidYearFormat() throws Exception {
        dateProcessor.process("21 01 03, 11 02 04");
        fail("Expected date format exception");
    }

    @Test
    public void shouldExpect365DaysDiffWhenNonLeapYear() throws Exception {
        Long expected = 365l;
        Long actualResponse = dateProcessor.process("11 10 2001, 11 10 2002");
        assertEquals(expected, actualResponse);
    }

    @Test
    public void shouldExpect366DaysDiffWhenLatestDateIsLeapYear() throws Exception {
        Long expected = 366l;
        Long actualResponse = dateProcessor.process("31 12 1999, 31 12 2000");
        assertEquals(expected, actualResponse);
    }

    @Test
    public void shouldExpect366DaysDiffWhenEarliestDateIsLeapYear() throws Exception {
        Long expected = 366l;
        Long actualResponse = dateProcessor.process("11 02 2000, 11 02 2001");
        assertEquals(expected, actualResponse);
    }

    @Test
    public void shouldExpect3652DaysDiffWhen10YearsWith2LeapYears() throws Exception {
        Long expected = 3652l;
        Long actualResponse = dateProcessor.process("11 02 1990, 11 02 2000");
        assertEquals(expected, actualResponse);
    }
}
