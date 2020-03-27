package org.shiloh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuickStartApplication {

    private static Logger logger = LoggerFactory.getLogger(QuickStartApplication.class);

    public static void main(String[] args) {
        logger.trace("***************log trace msg*****************");
        logger.info("***************log info msg*****************");
        logger.debug("***************log debug msg*****************");
        logger.warn("***************log warning msg*****************");
        logger.error("***************log error msg*****************");
        SpringApplication.run(QuickStartApplication.class, args);
    }

}
