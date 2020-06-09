package org.shiloh;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author lxlei
 */
@SpringBootApplication
public class QuickStartApplication {

    private static final Logger LOGGER = getLogger(QuickStartApplication.class);


    public static void main(String[] args) {
        LOGGER.trace("***************log trace msg*****************");
        LOGGER.info("***************log info msg*****************");
        LOGGER.debug("***************log debug msg*****************");
        LOGGER.warn("***************log warning msg*****************");
        LOGGER.error("***************log error msg*****************");
        SpringApplication.run(QuickStartApplication.class, args);
    }
}
