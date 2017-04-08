package com.aurea.ca.deadcode.reactive.Consumers;

import com.aurea.ca.deadcode.reactive.DeadCodeChannels;
import reactor.fn.Consumer;

/**
 * Created by ameen on 08/04/17.
 */
public interface DeadCodeConsumer<T> extends Consumer<T> {
    DeadCodeChannels getChannel();
}
