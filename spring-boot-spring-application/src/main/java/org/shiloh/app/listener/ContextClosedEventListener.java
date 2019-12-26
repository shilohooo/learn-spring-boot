package org.shiloh.app.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 9:07
 * @description
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        System.out.println("contextClosedEvent.getApplicationContext().getId() = " + contextClosedEvent.getApplicationContext().getId());
    }
}
