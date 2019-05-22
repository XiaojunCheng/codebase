package com.codebase.core.container;

public class Main {

    public static void main(String[] args) {
        BundleContainer container = new BundleContainer();
        container.start();
        container.stop();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
