package com.test.demafayz.testapplication.utils.sync;

import com.test.demafayz.testapplication.utils.TextUtil;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by demafayz on 25.08.16.
 */
public class ApiSync {

    public static String getBaseResponse(String path) {

        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            String result = readStream(is);
            String encode = encodeResult(result);
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String encodeResult(String result) throws UnsupportedEncodingException {

        String encoding = populateEncoding(result);
        if (encoding == null) return result;

        byte[] newResult = TextUtil.convertEncoding(result.getBytes(), encoding, "UTF-8");
        return new String(newResult);
    }

    private static String populateEncoding(String result) {
        String prefix = "encoding=\"";
        String suffix = "\"";
        String newResult = result.substring(result.indexOf(prefix) + prefix.length(), result.length());
        String encoding = newResult.substring(0, newResult.indexOf(suffix));
        if (encoding.equals("")) return null;
        return encoding;
    }

    private static String readStream(InputStream is) throws IOException {

        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String result = "";
        while ((line = reader.readLine()) != null) {
            result = result + line;
        }
        return result;
    }
}
