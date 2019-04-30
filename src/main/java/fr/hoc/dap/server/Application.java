package fr.hoc.dap.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main class executed at the launch of the app.
 * @author Mohammed et Thomas
 *
 */

@SpringBootApplication
public class Application {

    /** Logger Log4j declaration. */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * @param args Entry point.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
        LOG.info("Spring Boot initiated");
    }

    /**
     * create new configuration for the user.
     * @return the customized configuration
     */
    @Bean
    public Config config() {
        Config config = new Config();
        LOG.info("Configuration initialized");
        return config;

    }

    /**
     * @return The arguments for the command line runner in order to run tests.
     */

}
