package com.codebase.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;

public class ByteUtil {

    public static byte[] shortToBytes(short num) {
        byte[] b = new byte[2];
        for (int i = 0; i < 2; i++) {
            b[i] = (byte) (num >>> (i * 8));
        }
        return b;
    }

    public static short bytesToShort(byte[] data, int offset) {
        short num = 0;
        for (int i = offset + 1; i >= offset; i--) {
            num <<= 8;
            num |= (data[i] & 0xff);
        }
        return num;
    }

    public static byte[] intToBytes(int num) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (num >>> (i * 8));
        }
        return b;
    }

    public static int bytesToInt(byte[] data, int offset) {
        int num = 0;
        for (int i = offset + 3; i >= offset; i--) {
            num <<= 8;
            num |= (data[i] & 0xff);
        }
        return num;
    }

    public static byte[] longToBytes(long num) {
        byte[] b = new byte[8];
        for (int i = 0; i < 8; i++) {
            b[i] = (byte) (num >>> (i * 8));
        }
        return b;
    }

    public static long bytesToLong(byte[] data, int offset) {
        long num = 0;
        for (int i = offset + 7; i >= offset; i--) {
            num <<= 8;
            num |= (data[i] & 0xff);
        }
        return num;
    }

    public static byte[] uuidToByteArray(UUID sessionId) {

        byte[] part1 = longToBytes(sessionId.getMostSignificantBits());
        byte[] part2 = longToBytes(sessionId.getLeastSignificantBits());

        byte[] buffer = new byte[16];
        System.arraycopy(part1, 0, buffer, 0, 8);
        System.arraycopy(part2, 0, buffer, 8, 8);

        return buffer;
    }

    public static UUID bytesToUUID(byte[] data) {
        return new UUID(bytesToLong(data, 0), bytesToLong(data, 8));
    }

    public static int toUnsignedByte(byte data) {
        return data & 0x0FF;
    }
    
    /***
     * @param b 
     * @param position range from 0 to 7
     * @return
     */
    public static boolean getBitValue(byte b, int position) {

        if (position < 0 || position > 7) {
            throw new IllegalArgumentException("Out of byte index, position is " + position);
        }

        return ((b >> position) & 0x1) == 1 ? true : false;
    }

    /**
     * @param b
     * @param position range is from 0 to 7
     * @param flag  false then set as 0, true then set as 1
     * @return
     */
    public static byte setBitValue(byte b, int position, boolean flag) {

        if (flag == true) {
            // set the position th bit to 1:
            b = (byte) (b | (1 << position));
            return b;
        } else {
            // set the position th bit to zero:
            b = (byte) (b & ~(1 << position));
            return b;
        }
    }

    /**
     * @param b
     * @return a byte representation in string format
     */
    public static String byteToString(byte b) {
        return "" + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1) + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1) + (byte) ((b >> 3) & 0x1)
                + (byte) ((b >> 2) & 0x1) + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }

    public static Object bytesToObject(byte[] bytes) throws Exception {

        ObjectInputStream objInputStream = null;
        try {
            objInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            Object obj = objInputStream.readObject();
            return obj;
        } finally {
            if (objInputStream != null) {
                objInputStream.close();
            }
        }
    }

    public static byte[] objectToByte(Object obj) throws Exception {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objOutputStream = null;
        try {
            objOutputStream = new ObjectOutputStream(outputStream);
            objOutputStream.writeObject(obj);
            byte[] bytes = outputStream.toByteArray();
            return bytes;
        } finally {
            if (objOutputStream != null) {
                objOutputStream.close();
            }
        }
    }
}
