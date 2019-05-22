package com.codebase.foundation.apidesign.io;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/10
 */
public interface Receiver<T, ReceiverException extends Throwable> {

    void receive(T item) throws ReceiverException;

}
