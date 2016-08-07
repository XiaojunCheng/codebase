package com.codebase.core.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketConnection {

    private Socket socket;

    public SocketConnection(String host, int port) throws Exception {
        this(host, port, 0);
    }

    public SocketConnection(String host, int port, int timeoutInMills) throws Exception {
        socket = new Socket(host, port);
        if (timeoutInMills > 0) {
            socket.setSoTimeout(timeoutInMills);
        }
        socket.setTcpNoDelay(true);
    }

    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    public void close() {
        try {
            socket.close();
        } catch (Exception ignored) {
        }
    }

}
