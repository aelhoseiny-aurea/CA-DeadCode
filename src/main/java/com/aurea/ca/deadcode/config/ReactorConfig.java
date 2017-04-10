package com.aurea.ca.deadcode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.Environment;
import reactor.bus.EventBus;
import reactor.spring.context.config.EnableReactor;

/**
 * Created by ameen on 08/04/17.
 */
@Configuration
@EnableReactor
public class ReactorConfig {
    @Bean
    Environment env() {
        return Environment.initializeIfEmpty()
            .assignErrorJournal();
    }

    @Bean
    EventBus createEventBus(Environment env) {
        return EventBus.create(env, Environment.THREAD_POOL);
    }
}
