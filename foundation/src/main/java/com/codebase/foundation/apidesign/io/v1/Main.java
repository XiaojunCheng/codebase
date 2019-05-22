package com.codebase.foundation.apidesign.io.v1;

import java.io.*;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/10
 */
public class Main {

    public static void main(String[] args) throws IOException {

        File source = new File(Main.class.getResource("/MANIFEST.MF").getFile());
        File destination = File.createTempFile("satisfiedBy", ".txt");
        destination.deleteOnExit();
        BufferedReader reader = new BufferedReader(new FileReader(source));
        long count = 0;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    count++;
                    writer.append(line).append('\n');
                }
                writer.close();
            } catch (IOException e) {
                writer.close();
                destination.delete();
            }
        } finally {
            reader.close();
        }
        System.out.println(count);
    }

}
