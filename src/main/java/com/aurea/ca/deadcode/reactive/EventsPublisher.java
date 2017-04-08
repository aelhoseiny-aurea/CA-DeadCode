package com.aurea.ca.deadcode.reactive;

import com.aurea.ca.deadcode.reactive.Consumers.DeadCodeConsumer;
import com.aurea.ca.deadcode.reactive.events.NewProjectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import reactor.bus.Event;
import reactor.bus.EventBus;

import javax.annotation.PostConstruct;
import java.util.Collection;

import static reactor.bus.selector.Selectors.$;

/**
 * Created by ameen on 08/04/17.
 */
@Component
public class EventsPublisher {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    public void registerConsumers() {
        Collection<DeadCodeConsumer> deadCodeConsumers = webApplicationContext.getBeansOfType(DeadCodeConsumer.class).values();
        for (DeadCodeConsumer consumer : deadCodeConsumers) {
            eventBus.on($(consumer.getChannel()), consumer);
        }
    }

    public void publish(NewProjectEvent event) {
        eventBus.notify(event.getChannel(), Event.wrap(event));
    }
}
