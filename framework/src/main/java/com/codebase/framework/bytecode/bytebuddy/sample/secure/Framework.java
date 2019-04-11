package com.codebase.framework.bytecode.bytebuddy.sample.secure;

public interface Framework {

    <T> T secure(Class<T> type) throws Exception;

}
