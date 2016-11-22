package com.codebase.framework.paldb.api;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.Serializable;

public abstract class Serializer<Data> implements Serializable {

    public abstract <Data> Data read(DataInput input);

    public abstract <Data> void write(DataOutput output, Data data);

}
