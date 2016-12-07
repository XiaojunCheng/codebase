package com.codebase.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static long getInode(String file) throws IOException {
        return (long) Files.getAttribute(Paths.get(file), "unix:ino");
    }
}
