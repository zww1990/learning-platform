package io.example.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public enum MyObjectPool {
    INSTANCE;

    private final GenericObjectPool<MyObject> pool;

    MyObjectPool() {
        GenericObjectPoolConfig<MyObject> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(8);
        config.setMinIdle(2);
        config.setMaxIdle(4);
        config.setBlockWhenExhausted(true);
        config.setTestOnBorrow(true);
        config.setTestOnCreate(true);
        config.setTestOnReturn(true);
        config.setTestWhileIdle(true);
        MyObjectFactory factory = new MyObjectFactory();
        this.pool = new GenericObjectPool<>(factory, config);
    }

    public MyObject borrowObject() throws Exception {
        return this.pool.borrowObject();
    }

    public void returnObject(MyObject object) {
        this.pool.returnObject(object);
    }

    public int getNumActive() {
        return this.pool.getNumActive();
    }

    public int getNumIdle() {
        return this.pool.getNumIdle();
    }
}
