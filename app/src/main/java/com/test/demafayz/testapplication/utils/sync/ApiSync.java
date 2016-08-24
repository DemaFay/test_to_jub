package com.test.demafayz.testapplication.utils.sync;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
