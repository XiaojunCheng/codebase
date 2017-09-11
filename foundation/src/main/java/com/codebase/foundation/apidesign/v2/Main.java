package com.codebase.foundation.apidesign.v2;

import java.io.File;
import java.io.IOException;

/**
 * <a href=""></a>
 *
 * @author Xiaojun.Cheng
 * @date 2017/9/10
 */
public class Main {

    public static void main(String[] args) throws IOException {
        File source = new File(Main.class.getResource("/MANIFEST.MF").getFile());
        File destination = File.createTempFile("test", ".txt");
        destination.deleteOnExit();
        Inputs.text(source).transferTo(Transforms.filter(string -> string.length() != 0, Outputs.text(destination)));

        Functions.Counter counter = new Functions.Counter();
        Inputs.text(source).transferTo(Transforms.map(counter, Outputs.text(destination)));
        System.out.println(counter.getCount());
    }

}
