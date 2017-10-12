package cn.gridlife.bzblibrary;

import android.app.Application;
import android.content.Context;

import com.aispeech.common.AIConstant;


/**
 * Created by BZB on 2017/9/8.
 */

public class BZBLibraryAPP extends Application {
    private static Context context;

    //返回
    public static Context getContextObject(){
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        AIConstant.openLog();

    }
}
