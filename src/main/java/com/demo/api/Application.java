package com.demo.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * App Initializer for the Application
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@SpringBootApplication
public class Application {

    /** Logger declaration. */
    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    /**
     * Main method to initialize the spring boot api
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        } catch (Exception e) {
            LOGGER.error("Failed to start the app due to error = [{}", e.getMessage());
        }
    }
}
