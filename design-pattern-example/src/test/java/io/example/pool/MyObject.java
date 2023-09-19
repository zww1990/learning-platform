package io.example.pool;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class MyObject {
    private final String name;

    public MyObject(String name) {
        this.name = name;
    }

    public void create() {
        log.info("create(): 线程名={}, 对象名={}", Thread.currentThread().getName(), this.name);
    }

    public void destroy() {
        log.info("destroy(): 线程名={}, 对象名={}", Thread.currentThread().getName(), this.name);
    }

    public boolean isValid() {
        log.info("isValid(): 线程名={}, 对象名={}", Thread.currentThread().getName(), this.name);
        return true;
    }
}
