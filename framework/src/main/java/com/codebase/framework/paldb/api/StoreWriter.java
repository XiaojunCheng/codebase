package com.codebase.framework.paldb.api;


public interface StoreWriter {

    Configuration getConfiguration();

    void put(byte[] key, byte[] value);

    void put(Object key, Object value);

    void putAll(Object[] keys, Object[] values);

    void close();
}
