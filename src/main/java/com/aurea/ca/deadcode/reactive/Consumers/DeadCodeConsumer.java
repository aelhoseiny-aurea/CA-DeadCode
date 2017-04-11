package com.aurea.ca.deadcode.reactive.Consumers;

import com.aurea.ca.deadcode.reactive.DeadCodeChannels;
import reactor.fn.Consumer;

/**
 * Created by ameen on 08/04/17.
 */
public abstract class DeadCodeConsumer<T> implements Consumer<T> {

    public abstract DeadCodeChannels getChannel();

    public abstract void execute(T t);


    @Override
    public void accept(T t) {
        execute(t);

    }
}
