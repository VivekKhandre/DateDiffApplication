package com.ioof;

import com.ioof.service.DateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by jan on 22/10/17.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private DateProcessor dateProcessor;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args != null && args.length > 1) {
            dateProcessor.readAndProcess(args[1] != null ? args[1] : "application.properties");
        } else {
            dateProcessor.scanAndProcess();
        }
    }
}
