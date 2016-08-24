package com.test.demafayz.testapplication.utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by demafayz on 25.08.16.
 */
public class TextUtil {

    public static String convertToUTF8(String s, String encoding) {
        String out = null;
        try {
            out = new String(s.getBytes("UTF-8"), encoding);
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

    public static byte[] convertEncoding(byte[] bytes, String from, String to) throws UnsupportedEncodingException {
        return new String(bytes, from).getBytes(to);
    }
}
