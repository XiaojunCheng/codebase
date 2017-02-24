package com.codebase.core.container;

public interface BundleInitializer {

    void start(BundleContext context);

    void stop(BundleContext context);

}
