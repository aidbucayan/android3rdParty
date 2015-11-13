package bb.aid.com.android3rdparty.utils;

import android.util.Log;

/**
 * Logger class
 */
public class LogMe {
    private static final String APP_TAG = "Android3rdParty";

    public static void d(String TAG, String message) {
        if (Constants.ENABLE_LOG) {
            Log.d(APP_TAG + TAG, message);
        }
    }

    public static void e(String TAG, String message) {
        if (Constants.ENABLE_LOG) {
            Log.e(APP_TAG + TAG, message);
        }
    }

    public static void w(String TAG, String message) {
        if (Constants.ENABLE_LOG) {
            Log.w(APP_TAG + TAG, message);
        }
    }

    public static void v(String TAG, String message) {
        if (Constants.ENABLE_LOG) {
            Log.v(APP_TAG + TAG, message);
        }
    }

    public static void i(String TAG, String message) {
        if (Constants.ENABLE_LOG) {
            Log.i(APP_TAG + TAG, message);
        }
    }
}

