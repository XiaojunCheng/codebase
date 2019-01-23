package com.codebase.common.config;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/19
 */
public class ConfigException extends Exception {

    public ConfigException(Throwable cause) {
        super(cause);
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigException(String message) {
        super(message);
    }
}
