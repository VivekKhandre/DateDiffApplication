package com.ioof.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class IoofDateTests {

    @TestConfiguration
    static class IoofDateTestContextConfiguration {
        @Bean
        public IoofDate validDate() {
            return new IoofDate("11 02 2001");
        }

        @Bean
        public IoofDate invalidDate() {
            return new IoofDate("22 02 1899");
        }

        @Bean
        public IoofDate anotherValidDate() {
            return new IoofDate("11 03 1999");
        }

        @Bean
        public IoofDate leapYearDate() {
            return new IoofDate("11 03 2000");
        }

    }

    @Autowired
    private IoofDate validDate, invalidDate, leapYearDate, anotherValidDate;

    @Test
    public void shouldExpectValidDateFor_11_02_2001() {
        assertEquals(11, validDate.getDay().getValue());
        assertEquals(2, validDate.getMonth().getValue());
        assertEquals(2001, validDate.getYear().getValue());
        assertEquals(true, validDate.isValid());
        Long expectedTotalDays = 36932l;
        assertEquals(expectedTotalDays, validDate.getTotalDays());
    }

    @Test
    public void shouldExpectInvalidDateFor_22_02_1899() {
        assertEquals(false, invalidDate.isValid());
    }

    @Test
    public void shouldExpectLeapYearDateFor_11_03_2000() {
        Long expectedTotalDays = 36595l;
        assertEquals(true, leapYearDate.isValid());
        assertEquals(expectedTotalDays, leapYearDate.getTotalDays());
    }

    @Test
    public void shouldExpectLeapYearDateDiffWithAnotherValidDate() {
        Long expectedTotalDays = 366l;
        assertEquals(expectedTotalDays, leapYearDate.difference(anotherValidDate));
    }

}
