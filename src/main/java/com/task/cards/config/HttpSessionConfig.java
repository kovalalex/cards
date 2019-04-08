package com.task.cards.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Счетчик с сессиями
 */
@Configuration
public class HttpSessionConfig {

    public static AtomicInteger onlineCounter = new AtomicInteger(0);
    public static AtomicInteger allRequestCounter = new AtomicInteger(0);


    @Bean                           // bean для http session listener
    public HttpSessionListener httpSessionListener() {
        return new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent se) {               // Метод вызываемый при создании сессии
                System.out.println("Session Created with session id+" + se.getSession().getId());
                se.getSession().setMaxInactiveInterval(60); //Время жизни минута

                onlineCounter.incrementAndGet();
                allRequestCounter.incrementAndGet();
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent se) {         // Метод вызываемый при автоматическом удалении
                System.out.println("Session Destroyed, Session id:" + se.getSession().getId());
                onlineCounter.decrementAndGet();

            }
        };
    }
}
