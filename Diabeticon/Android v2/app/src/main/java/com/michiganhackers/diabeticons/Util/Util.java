package com.michiganhackers.diabeticons.Util;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by jawad on 12/10/15.
 */
public class Util {
    public static String KEY_PATH = "PathKey";
    public static String KEY_TITLE = "TitleKey";

    // Easily encodes a url
    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            Log.wtf("MD/Util", "UTF-8 should always be supported", e);
            throw new RuntimeException("URLEncoder.encode() failed for " + s);
        }
    }
}
