package com.test.demafayz.testapplication.utils;

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
}
