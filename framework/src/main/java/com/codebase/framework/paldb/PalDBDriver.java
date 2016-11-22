package com.codebase.framework.paldb;

import com.linkedin.paldb.api.PalDB;
import com.linkedin.paldb.api.StoreReader;
import com.linkedin.paldb.api.StoreWriter;

import java.io.File;
import java.util.Map;

/**
 * Created by chengxiaojun on 16/11/21.
 */
public class PalDBDriver {

    public static void main(String[] args) {
        File storeFile = new File("store.paldb");
        System.out.println(storeFile.getAbsolutePath());
        StoreWriter writer = PalDB.createWriter(storeFile);
        writer.put("foo", "bar");
        writer.put(1213, new int[]{1, 2, 3});
        writer.put(new int[]{1, 2, 3}, new int[]{4, 5, 6});
        writer.close();

        StoreReader reader = PalDB.createReader(storeFile);
        String val1 = reader.get("foo");
        int[] val2 = reader.get(1213);
        System.out.println("key: " + val1);
        for (int v : val2) {
            System.out.println("value: " + v);
        }

        //iterator
//        Iterable<Map.Entry<String, String>> iterable = reader.iterable();
//        for (Map.Entry<String, String> entry : iterable) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            System.out.println("key: " + key + ", value: " + value);
//        }
        reader.close();
    }

}
