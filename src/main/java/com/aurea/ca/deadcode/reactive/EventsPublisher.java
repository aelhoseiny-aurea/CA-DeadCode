package com.aurea.ca.deadcode.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.bus.Event;
import reactor.bus.EventBus;

/**
 * Created by ameen on 08/04/17.
 */
@Component
public class EventsPublisher {

    @Autowired
    private EventBus eventBus;

    public void publish(Object data, DeadCodeChannels channel) {
        eventBus.notify(channel, Event.wrap(data));
    }
}
