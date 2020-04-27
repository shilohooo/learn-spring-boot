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

    public static final Logger logger = getLogger(QuickStartApplication.class);

    public static void main(String[] args) {
        logger.trace("***************log trace msg*****************");
        logger.info("***************log info msg*****************");
        logger.debug("***************log debug msg*****************");
        logger.warn("***************log warning msg*****************");
        logger.error("***************log error msg*****************");
        SpringApplication.run(QuickStartApplication.class, args);
    }
}
