package com.codebase.foundation.apidesign.io;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/10
 */
public interface Sender<T, SenderException extends Throwable> {

    <ReceiverException extends Throwable> void sendTo(Receiver<T, ReceiverException> receiver) throws ReceiverException, SenderException;

}
