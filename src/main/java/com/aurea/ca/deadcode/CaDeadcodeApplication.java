package com.aurea.ca.deadcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.Environment;
import reactor.bus.EventBus;
import reactor.spring.context.config.EnableReactor;

@SpringBootApplication
@EnableReactor
public class CaDeadcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaDeadcodeApplication.class, args);
    }

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
