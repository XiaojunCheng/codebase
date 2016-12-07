package com.codebase.framework.posix;

import com.codebase.common.util.FileUtil;
import jnr.posix.DummyPOSIXHandler;
import jnr.posix.FileStat;
import jnr.posix.POSIX;
import jnr.posix.POSIXFactory;

import java.io.IOException;

/**
 * Created by chengxiaojun on 16/12/6.
 */
public class PosixDriver {

    public static void main(String[] args) throws IOException {
        String file = "/Users/koudai213/Documents/Workspace/GithubRepository/Git.md";
        long inode = FileUtil.getInode(file);
        System.out.println(inode);

        POSIX posix = POSIXFactory.getPOSIX(new DummyPOSIXHandler(), true);
        FileStat stat = posix.stat(file);
        System.out.println(stat.ino());
    }
}
