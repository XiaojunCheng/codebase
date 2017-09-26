package com.codebase.foundation.apidesign.io;

import com.codebase.foundation.apidesign.functional.Functions;

import java.io.File;
import java.io.IOException;

/**
 * <a href="https://github.com/oldratlee/translations/blob/master/generic-io-api-in-java-and-api-design/README.md">Java的通用I/O API</a>
 *
 * @author Xiaojun.Cheng
 * @date 2017/9/10
 */
public class Main {

    public static void main(String[] args) throws IOException {
        File source = new File(Main.class.getResource("/MANIFEST.MF").getFile());
        File destination = File.createTempFile("satisfiedBy", ".txt");
        destination.deleteOnExit();
        Inputs.text(source).transferTo(Transforms.filter(string -> string.length() != 0, Outputs.text(destination)));

        Functions.Counter counter = new Functions.Counter();
        Inputs.text(source).transferTo(Transforms.map(counter, Outputs.text(destination)));
        System.out.println(counter.getCount());

        Functions.Counter counter2 = new Functions.Counter();
        Inputs.text(source).transferTo(Transforms.filterMap(string -> string.length() != 0, counter2, Outputs.text(destination)));
        System.out.println(counter2.getCount());
    }

}
