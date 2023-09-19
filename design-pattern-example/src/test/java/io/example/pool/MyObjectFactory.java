package io.example.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.UUID;

public class MyObjectFactory extends BasePooledObjectFactory<MyObject> {
    @Override
    public MyObject create() {
        MyObject object = new MyObject(UUID.randomUUID().toString());
        object.create();
        return object;
    }

    @Override
    public PooledObject<MyObject> wrap(MyObject obj) {
        return new DefaultPooledObject<>(obj);
    }

    @Override
    public void destroyObject(PooledObject<MyObject> p) {
        p.getObject().destroy();
    }

    @Override
    public boolean validateObject(PooledObject<MyObject> p) {
        return p.getObject().isValid();
    }
}
