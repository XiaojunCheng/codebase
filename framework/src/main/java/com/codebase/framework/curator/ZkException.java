package com.codebase.framework.curator;

public class ZkException extends Exception {

    private static final long serialVersionUID = 34511497671701173L;

    public ZkException() {
        super();
    }

    public ZkException(String message) {
        super(message);
    }

    public ZkException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZkException(Throwable cause) {
        super(cause);
    }
}
