package com.test.demafayz.testapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * Created by demafayz on 25.08.16.
 */
public class AppUtil {
    public static final boolean DEBUG = true;

    public static void dLog(Class objectClass, String text) {
        if (DEBUG) {
            Log.d(objectClass.getSimpleName(), text);
        }
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }
}
