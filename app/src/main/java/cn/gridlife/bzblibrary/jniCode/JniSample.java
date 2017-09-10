package cn.gridlife.bzblibrary.jniCode;

/**
 * Created by BZB on 2017/9/7.
 */

public class JniSample {
    static {
        System.loadLibrary("native-lib");
    }
    public static native String stringFromJNI();
}
