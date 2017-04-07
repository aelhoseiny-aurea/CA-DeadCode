package com.aurea.ca.deadcode.reactive.Consumers;

import com.aurea.ca.deadcode.reactive.events.NewProjectEvent;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;

/**
 * Created by ameen on 08/04/17.
 */
@Service
public class NewProjectConsumer implements Consumer<Event<NewProjectEvent>> {


    @Override
    public void accept(Event<NewProjectEvent> newProjectEventEvent) {
        System.out.println("project added !!!!!");
    }
}
