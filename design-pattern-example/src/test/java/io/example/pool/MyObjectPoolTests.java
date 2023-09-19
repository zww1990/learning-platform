package io.example.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
public class MyObjectPoolTests {
    public static void main(String[] args) throws Exception {
//        testSingle();
        testThread();
    }

    private static void numActiveAndIdle(MyObjectPool pool) {
        log.info("numActiveAndIdle(): ThreadName={}, NumActive={}, NumIdle={}",
                Thread.currentThread().getName(), pool.getNumActive(), pool.getNumIdle());
    }

    private static void testSingle() throws Exception {
        MyObjectPool pool = MyObjectPool.INSTANCE;
        numActiveAndIdle(pool);

        Thread.sleep(1000);
        MyObject object = pool.borrowObject();

        log.info("ThreadName={}, borrowed={}", Thread.currentThread().getName(), object);

        Thread.sleep(1000);
        numActiveAndIdle(pool);

        Thread.sleep(1000);
        pool.returnObject(object);

        log.info("ThreadName={}, returned={}", Thread.currentThread().getName(), object);

        Thread.sleep(1000);
        numActiveAndIdle(pool);
    }

    private static void testThread() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(9);
        IntStream.range(0, 20).forEach(i ->
                service.submit(() -> {
                    try {
                        testSingle();
                    } catch (Exception e) {
                        log.error(e.getLocalizedMessage(), e);
                    }
                })
        );
        service.shutdown();
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }
}
