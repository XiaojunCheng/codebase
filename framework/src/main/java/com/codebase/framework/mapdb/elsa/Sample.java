package com.codebase.framework.mapdb.elsa;

import org.mapdb.elsa.ElsaMaker;
import org.mapdb.elsa.ElsaSerializerPojo;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by chengxiaojun on 16/12/7.
 */
public class Sample {

    public static void main(String[] args) throws IOException {
        //some data to be serialized
        String data = "Hello World";

        //construct Elsa serializer
        ElsaSerializerPojo serializer = new ElsaMaker().make();

        //Elsa takes DataOutput and DataInput. Construct them on top of OutputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream out2 = new DataOutputStream(out);

        serializer.serialize(out2, data);

        //now deserialize data using DataInput
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(out.toByteArray()));

        String data2 = serializer.deserialize(in);
        assertEquals(data, data2);
    }

}
