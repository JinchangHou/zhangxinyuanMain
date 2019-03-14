package com.example.volunteer.Utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by asus on 2018/11/5.
 */

public class NetApplication extends Application {
    private static NetApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;

    }
    /**获取上下文*/
    public static Context getApplication() {
        return application;
    }
}
