package com.codebase.foundation.performance;

public interface TestCase {

    String getName();

    void init() throws Exception;

    Object action() throws Exception;

}
