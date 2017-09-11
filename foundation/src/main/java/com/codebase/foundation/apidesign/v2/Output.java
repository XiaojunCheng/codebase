package com.codebase.foundation.apidesign.v2;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/10
 */
public interface Output<T, ReceiverException extends Throwable> {

    <SenderException extends Throwable> void receiveFrom(Sender<T, SenderException> sender) throws SenderException, ReceiverException;

}

