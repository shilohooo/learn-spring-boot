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

    private static final Logger LOG = getLogger(QuickStartApplication.class);

    public static void main(String[] args) {
        LOG.trace("***************log trace msg*****************");
        LOG.info("***************log info msg*****************");
        LOG.debug("***************log debug msg*****************");
        LOG.warn("***************log warning msg*****************");
        LOG.error("***************log error msg*****************");
        SpringApplication.run(QuickStartApplication.class, args);
    }
}
