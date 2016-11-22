package com.codebase.framework.paldb.api.impl;

import com.codebase.framework.paldb.api.Configuration;
import com.codebase.framework.paldb.api.StoreWriter;

class WriterImpl implements StoreWriter {

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public void put(byte[] key, byte[] value) {
        doPut(key, value);
    }

    @Override
    public void put(Object key, Object value) {

    }

    @Override
    public void putAll(Object[] keys, Object[] values) {

    }

    private void doPut(byte[] key, byte[] value) {
        //TODO
    }

    @Override
    public void close() {

    }
}
