package com.codebase.foundation.apidesign.io;

import com.codebase.foundation.apidesign.functional.Function;
import com.codebase.foundation.apidesign.functional.Specification;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/11
 */
public class Transforms {

    public static <T, ReceiverException extends Throwable>
    Output<T, ReceiverException> filterMap(final Specification<T> specification,
                                           final Function<T, T> function,
                                           final Output<T, ReceiverException> output) {
        return new Output<T, ReceiverException>() {
            @Override
            public <SenderException extends Throwable> void receiveFrom(Sender<T, SenderException> sender) throws
                    SenderException, ReceiverException {
                output.receiveFrom(new Sender<T, SenderException>() {
                    @Override
                    public <ReceiverException extends Throwable> void sendTo(Receiver<T, ReceiverException> receiver)
                            throws ReceiverException, SenderException {
                        sender.sendTo(item -> {
                            if (specification.satisfiedBy(item)) {
                                T transferItem = function.map(item);
                                receiver.receive(transferItem);
                            }
                        });
                    }
                });
            }
        };
    }

    public static <T, ReceiverException extends Throwable>
    Output<T, ReceiverException> filter(final Specification<T> specification,
                                        final Output<T, ReceiverException> output) {
        return new Output<T, ReceiverException>() {
            @Override
            public <SenderException extends Throwable> void receiveFrom(Sender<T, SenderException> sender) throws
                    SenderException, ReceiverException {
                output.receiveFrom(new Sender<T, SenderException>() {
                    @Override
                    public <ReceiverException extends Throwable> void sendTo(Receiver<T, ReceiverException> receiver) throws
                            ReceiverException, SenderException {
                        sender.sendTo(item -> {
                            if (specification.satisfiedBy(item)) {
                                receiver.receive(item);
                            }
                        });
                    }
                });
            }
        };
    }

    public static <From, To, ReceiverException extends Throwable>
    Output<From, ReceiverException> map(final Function<From, To> function, final Output<To, ReceiverException> output) {
        return new Output<From, ReceiverException>() {
            @Override
            public <SenderException extends Throwable> void receiveFrom(Sender<From, SenderException> sender) throws
                    SenderException, ReceiverException {
                output.receiveFrom(new Sender<To, SenderException>() {
                    @Override
                    public <ReceiverException extends Throwable> void sendTo(Receiver<To, ReceiverException> receiver) throws
                            ReceiverException, SenderException {
                        sender.sendTo(item -> {
                            To transferItem = function.map(item);
                            receiver.receive(transferItem);
                        });
                    }
                });
            }
        };
    }

}
