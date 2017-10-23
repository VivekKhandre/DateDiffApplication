package com.ioof.service;

import com.ioof.date.IoofDate;
import com.ioof.exception.DateFormatException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by jan on 22/10/17.
 * DateProcessor will scan from command line input or will read a file and then process the dates pairs.
 */
@Service
public class DateProcessor {
    private IoofDate earliestDate, latestDate;
    private Boolean keepExecuting = true;


    /**
     * This method scans the string and then process the pair of dates to get the difference or displays the error. It will continue until 'Exit' keyword is entered or terminated.
     */
    public void scanAndProcess() {
        System.out.println("\nEnter pairs of dates starting from 1900 where the first date should be earliest, the second date the latest");
        System.out.println("Enter pairs of dates the following format or 'Exit' to terminate ");
        System.out.println("DD MM YYYY, DD MM YYYY");

        while (keepExecuting) {
            System.out.println(">>");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                String datePairsLine = reader.readLine();

                if (datePairsLine.equalsIgnoreCase("exit")) {
                    keepExecuting = false;
                    System.out.println("Exiting application.");
                } else {
                    System.out.println("Output: " + datePairsLine + ", " + process(datePairsLine));
                }

            } catch (Exception e) {
                System.out.println("Output: " + e.getMessage());
            }
        }
    }

    /**
     * This method reads the lines from file and then process the pair of dates to get the difference or displays the error.
     */
    public String readAndProcess(String fileName) {
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String datePairsLine = scanner.nextLine();
                try {
                    System.out.println("Entered: " + datePairsLine);
                    Long response = process(datePairsLine);
                    System.out.println("Output: " + datePairsLine + ", " + response + "\n");
                } catch (Exception e) {
                    System.out.println("Output: " + e.getMessage() + "\n");
                }
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            process("");
        } catch (Exception e) {
            e.getMessage();
        }
        return "";
    }

    /**
     * This method accepts string of dates pairs and then creates Date class to
     */
    public Long process(String dateStr) throws DateFormatException {
        String dates[] = dateStr.split(",");
        if (dates.length < 2) {
            throw new DateFormatException("Invalid dates. Enter pairs of dates,  'DD MM YYYY,DD MM YYYY'");
        }
        earliestDate = new IoofDate(dates[0].trim());
        latestDate = new IoofDate(dates[1].trim());
        if (!earliestDate.isValid() || !latestDate.isValid()) {
            throw new DateFormatException("Invalid date format. Enter dates,  'DD MM YYYY,DD MM YYYY'");
        } else if (!(earliestDate.getTotalDays() <= latestDate.getTotalDays())) {
            throw new DateFormatException("Invalid sequence. Enter 'Earliest Date, Latest Date'");
        }
        return latestDate.difference(earliestDate);
    }
}
