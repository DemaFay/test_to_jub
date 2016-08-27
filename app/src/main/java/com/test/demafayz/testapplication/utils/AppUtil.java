package com.test.demafayz.testapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        } else {
            return true;
        }
    }
}
