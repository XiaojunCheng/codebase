package com.codebase.foundation.apidesign.io;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/10
 */
public interface Input<T, SenderException extends Throwable> {

    <ReceiverException extends Throwable> void transferTo(Output<T, ReceiverException> output) throws SenderException, ReceiverException;

}
