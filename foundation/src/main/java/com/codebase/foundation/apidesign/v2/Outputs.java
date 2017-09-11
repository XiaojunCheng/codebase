package com.codebase.foundation.apidesign.v2;

import java.io.*;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/10
 */
public class Outputs {

    public static Output<String, IOException> text(File destination) throws IOException {
        return new TextOutput(destination);
    }

    static class TextOutput implements Output<String, IOException> {

        private final File destination;
        private Writer writer;

        public TextOutput(File destination) throws IOException {
            this.destination = destination;
            this.writer = new FileWriter(this.destination);
        }

        @Override
        public <SenderException extends Throwable> void receiveFrom(Sender<String, SenderException> sender) throws IOException, SenderException {
            StringReceiver receiver = new StringReceiver(writer);
            try {
                sender.sendTo(receiver);
            } finally {
                receiver.onFinished();
                onFinished();
            }
        }

        public void onFinished() throws IOException {
            writer.close();
        }
    }

    static class StringReceiver implements Receiver<String, IOException> {

        private final Writer writer;
        private BufferedWriter bufferedWriter;

        public StringReceiver(Writer writer) {
            this.writer = writer;
            this.bufferedWriter = new BufferedWriter(this.writer);
        }

        @Override
        public void receive(String item) throws IOException {
            bufferedWriter.append(item).append('\n');
        }

        public void onFinished() throws IOException {
            bufferedWriter.close();
        }
    }

}
