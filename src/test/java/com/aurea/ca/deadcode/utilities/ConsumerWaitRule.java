package com.aurea.ca.deadcode.utilities;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Phaser;

/**
 * Created by ameen on 11/04/17.
 */
@Component
public class ConsumerWaitRule extends TestWatcher {

    @Autowired
    private Phaser phaser;

    @Override
    protected void failed(Throwable e, Description description) {
//        phaser.arriveAndAwaitAdvance();
//        super.failed(e, description);
    }
}
