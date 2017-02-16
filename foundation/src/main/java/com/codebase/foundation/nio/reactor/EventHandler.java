package com.codebase.foundation.nio.reactor;

import java.nio.channels.SelectionKey;

public interface EventHandler {

    public void handleEvent(SelectionKey handle) throws Exception;

}
