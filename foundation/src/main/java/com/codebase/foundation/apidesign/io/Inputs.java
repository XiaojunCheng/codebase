package com.codebase.foundation.apidesign.io;

import java.io.*;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/10
 */
public class Inputs {

    public static Input<String, IOException> text(File source) throws IOException {
        return new TextInput(source);
    }

    static class TextInput implements Input<String, IOException> {

        private final File source;
        private Reader reader;

        public TextInput(File source) throws IOException {
            this.source = source;
            this.reader = new FileReader(this.source);
        }

        @Override
        public <ReceiverException extends Throwable> void transferTo(Output<String, ReceiverException> output) throws IOException, ReceiverException {
            TextSender sender = new TextSender(reader);
            try {
                output.receiveFrom(sender);
            } finally {
                sender.onFinished();
                onFinished();
            }
        }

        public void onFinished() throws IOException {
            reader.close();
        }
    }

    static class TextSender implements Sender<String, IOException> {

        private final Reader reader;
        private BufferedReader bufferReader;

        public TextSender(Reader reader) {
            this.reader = reader;
            this.bufferReader = new BufferedReader(this.reader);
        }

        @Override
        public <ReceiverException extends Throwable> void sendTo(Receiver<String, ReceiverException> receiver) throws ReceiverException, IOException {
            String line;
            while ((line = bufferReader.readLine()) != null) {
                receiver.receive(line);
            }
        }

        public void onFinished() throws IOException {
            bufferReader.close();
        }
    }

}
