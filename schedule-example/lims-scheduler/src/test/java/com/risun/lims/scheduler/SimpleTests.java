package com.risun.lims.scheduler;

import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

public class SimpleTests {
    @Test
    public void testTimeUnit() {
        System.err.println(SECONDS.toMillis(5));
        System.err.println(MINUTES.toMillis(10));
    }
}
