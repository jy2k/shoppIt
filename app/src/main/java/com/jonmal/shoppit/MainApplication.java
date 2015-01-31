package com.jonmal.shoppit;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

public class MainApplication extends Application {

    //==========================================================
    //                  Constants
    //==========================================================

    private static final String SHARED_PREFS_NAME = "com.jonmal.SHARED_PREFS_NAME";

    //==========================================================
    //                  Fields
    //==========================================================

    private static Context mAppContext;
    private static Handler mMainThreadHandler;
    private static SharedPreferences prefs;

    //==========================================================
    //                  Constructor
    //==========================================================


    @Override
    public void onCreate() {
        super.onCreate();

        mAppContext = this;
        mMainThreadHandler = new Handler();
        prefs = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
    }

    //===========================================================
    // 							Public Methods
    //===========================================================

    public static Context getAppContext() {
        return mAppContext;
    }

    public static Handler getUIHandler() {
        return mMainThreadHandler;
    }

    public static SharedPreferences getSharedPreferences() {
        return prefs;
    }
}
