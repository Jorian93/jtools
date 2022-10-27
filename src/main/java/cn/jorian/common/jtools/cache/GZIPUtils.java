package cn.jorian.common.jtools.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 * 
 * description 压缩解压缩类
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:09:09 PM
 *
 */
public class GZIPUtils {
    /**
     * 使用gzip进行压缩
     */
    @SuppressWarnings("restriction")
    public static String compress(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }
        // 不需要关流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(gzip);
        }
        return new sun.misc.BASE64Encoder().encode(out.toByteArray());
    }

    /**
     * 使用gzip进行解压缩
     */
    @SuppressWarnings("restriction")
    public static String uncompress(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(ginzip);
            closeQuietly(in);
            closeQuietly(out);
        }
        return decompressed;
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }
}
