package com.codebase.foundation.apidesign.v2;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/11
 */
public class Transforms {

    public static <T, ReceiverException extends Throwable> Output<T, ReceiverException> filter(final Specification<T> specification, final Output<T, ReceiverException> output) {
        return new SpecificationOutputWrapper<>(specification, output);
    }

    public static <From, To, ReceiverException extends Throwable> Output<From, ReceiverException> map(final Function<From, To> function, final Output<To, ReceiverException> output) {
        return new FunctionOutputWrapper<>(function, output);
    }

    static class FunctionOutputWrapper<From, To, ReceiverException extends Throwable> implements Output<From, ReceiverException> {

        private final Function<From, To> function;
        private final Output<To, ReceiverException> output;

        public FunctionOutputWrapper(Function<From, To> function, Output<To, ReceiverException> output) {
            this.function = function;
            this.output = output;
        }

        @Override
        public <SenderException extends Throwable> void receiveFrom(Sender<From, SenderException> sender) throws SenderException, ReceiverException {
            output.receiveFrom(new FunctionSendWrapper<>(function, sender));
        }
    }

    static class FunctionSendWrapper<From, To, SenderException extends Throwable> implements Sender<To, SenderException> {

        private final Function<From, To> function;
        private final Sender<From, SenderException> sender;

        public FunctionSendWrapper(Function<From, To> function, Sender<From, SenderException> sender) {
            this.function = function;
            this.sender = sender;
        }

        @Override
        public <ReceiverException extends Throwable> void sendTo(Receiver<To, ReceiverException> receiver) throws ReceiverException, SenderException {
            sender.sendTo(new FunctionReceiverWrapper<>(function, receiver));
        }
    }

    static class FunctionReceiverWrapper<From, To, ReceiverException extends Throwable> implements Receiver<From, ReceiverException> {

        private final Function<From, To> function;
        private final Receiver<To, ReceiverException> receiver;

        public FunctionReceiverWrapper(Function<From, To> function, Receiver<To, ReceiverException> receiver) {
            this.function = function;
            this.receiver = receiver;
        }

        @Override
        public void receive(From item) throws ReceiverException {
            To transferItem = function.map(item);
            receiver.receive(transferItem);
        }
    }


    static class SpecificationOutputWrapper<T, ReceiverException extends Throwable> implements Output<T, ReceiverException> {

        private final Specification<T> specification;
        private final Output<T, ReceiverException> output;

        public SpecificationOutputWrapper(Specification<T> specification, Output<T, ReceiverException> output) {
            this.specification = specification;
            this.output = output;
        }

        @Override
        public <SenderException extends Throwable> void receiveFrom(Sender<T, SenderException> sender) throws SenderException, ReceiverException {
            output.receiveFrom(new SpecificationSenderWrapper<>(specification, sender));
        }
    }

    static class SpecificationSenderWrapper<T, SenderException extends Throwable> implements Sender<T, SenderException> {

        private final Specification<T> specification;
        private final Sender<T, SenderException> sender;

        public SpecificationSenderWrapper(Specification<T> specification, Sender<T, SenderException> sender) {
            this.specification = specification;
            this.sender = sender;
        }

        @Override
        public <ReceiverException extends Throwable> void sendTo(Receiver<T, ReceiverException> receiver) throws ReceiverException, SenderException {
            sender.sendTo(new SpecificationReceiverWrapper<>(specification, receiver));
        }
    }

    static class SpecificationReceiverWrapper<T, ReceiverException extends Throwable> implements Receiver<T, ReceiverException> {

        private final Specification<T> specification;
        private final Receiver<T, ReceiverException> receiver;

        public SpecificationReceiverWrapper(Specification<T> specification, Receiver<T, ReceiverException> receiver) {
            this.specification = specification;
            this.receiver = receiver;
        }

        @Override
        public void receive(T item) throws ReceiverException {
            if (specification != null && specification.test(item)) {
                receiver.receive(item);
            }
        }
    }

}
