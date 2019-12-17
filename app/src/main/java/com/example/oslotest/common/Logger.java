package com.example.oslotest.common;

import android.util.Log;

public class Logger {

    private static final String APP_TAG = "olsotest";

    public static void d(String tag, String msg) {
        Log.d(APP_TAG, "[" + tag + "]" + msg);
    }

    public static void e(String tag, String msg) {
        Log.e(APP_TAG, "[" + tag + "]" + msg);
    }
}
