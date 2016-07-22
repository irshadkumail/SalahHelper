package com.sodo.kumail.salahhelper;

import android.app.Application;
import android.os.Build;

/**
 * Created by kumail on 7/16/2016.
 */

public class MyApplication extends Application {

    public static int CURRENT_VERSION= Build.VERSION.SDK_INT;
    public  static MyApplication myApplication=null;

    public void onCreate()
    {
        super.onCreate();
        myApplication=this;

    }

    public static MyApplication getMyApplication()
    {
        return myApplication;
    }


}
