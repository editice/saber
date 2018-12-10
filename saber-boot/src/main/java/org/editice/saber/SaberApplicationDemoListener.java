package org.editice.saber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

/**
 * @author tinglang
 * @date 2018/12/7.
 */
@Order(1)
public class SaberApplicationDemoListener implements ApplicationListener {

    private static final Logger logger = LoggerFactory.getLogger(SaberApplicationDemoListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // 监听ApplicationStartedEvent
        if (event instanceof ApplicationStartingEvent) {
            System.out.println("Awesome! application started!");
        }

        //备注，日志初始化也是基于startingEvent去实现的监听器，因此第一行使用了sout的方式
        // 监听ApplicationEnvironmentPreparedEvent
        else if (event instanceof ApplicationEnvironmentPreparedEvent) {
            logger.warn("Awesome! application env prepared!");
        }
        // 监听ApplicationPreparedEvent
        else if (event instanceof ApplicationPreparedEvent) {
            logger.warn("Awesome! application prepared!");
        }

        // 监听ApplicationReadyEvent
        else if (event instanceof ApplicationReadyEvent) {
            logger.warn("Awesome! application was ready!");
        }

        // 监听ApplicationFailedEvent
        else if (event instanceof ApplicationFailedEvent) {
            logger.warn("Help me! application failed!");
        }
    }
}
