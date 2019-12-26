package org.shiloh.app.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.Ordered;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 9:09
 * @description
 */
public class AfterContextClosedEventListener implements ApplicationListener<ContextClosedEvent>, Ordered {
    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        System.out.println("afterContextClosedEvent.getApplicationContext().getId() = " + contextClosedEvent.getApplicationContext().getId());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
