package com.codebase.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

public class CompressUtil {

    public static byte[] compressInGzip(byte[] originalData) throws Exception {
        return compressInGzip(originalData, 0, originalData.length);
    }

    public static byte[] compressInGzip(byte[] originalData, int offset, int length) throws Exception {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutStream = new GZIPOutputStream(bos);
        gzipOutStream.write(originalData, offset, length);
        gzipOutStream.finish();
        gzipOutStream.flush();
        gzipOutStream.close();
        byte[] compressData = bos.toByteArray();
        bos.close();

        return compressData;
    }

    public static byte[] decompressInGzip(byte[] compressData) throws Exception {
        return decompressInGzip(compressData, 0, compressData.length);
    }

    public static byte[] decompressInGzip(byte[] compressData, int offset, int length) throws Exception {

        ByteArrayInputStream bis = new ByteArrayInputStream(compressData, offset, length);
        GZIPInputStream gzipInStream = new GZIPInputStream(bis);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int count;
        byte[] buf = new byte[1024];
        while ((count = gzipInStream.read(buf)) > 0) {
            bos.write(buf, 0, count);
        }
        gzipInStream.close();

        byte[] originalData = bos.toByteArray();
        bos.close();

        return originalData;
    }

    public static byte[] compressInZlib(byte[] originalData) throws Exception {
        return compressInZlib(originalData, 0, originalData.length);
    }

    public static byte[] compressInZlib(byte[] originalData, int offset, int length) throws IOException {

        Deflater compresser = new Deflater();
        compresser.setInput(originalData, offset, length);
        compresser.finish();

        ByteArrayOutputStream bos = new ByteArrayOutputStream(length);

        int count;
        byte[] buf = new byte[1024];
        while (!compresser.finished()) {
            count = compresser.deflate(buf);
            bos.write(buf, 0, count);
        }
        compresser.end();

        byte[] compressData = bos.toByteArray();
        bos.close();

        return compressData;
    }

    public static byte[] decompressInZlib(byte[] compressData) throws Exception {
        return decompressInZlib(compressData, 0, compressData.length);
    }

    public static byte[] decompressInZlib(byte[] compressData, int offset, int length) throws Exception {

        Inflater decompresser = new Inflater();
        decompresser.setInput(compressData, 0, compressData.length);

        ByteArrayOutputStream bos = new ByteArrayOutputStream(length);

        int count;
        byte[] buf = new byte[1024];
        while (!decompresser.finished()) {
            count = decompresser.inflate(buf);
            bos.write(buf, 0, count);
        }

        byte[] originalData = bos.toByteArray();
        decompresser.end();

        return originalData;
    }
}
