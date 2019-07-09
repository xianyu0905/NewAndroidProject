package com.zww149.androidtraning1.application;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this,"2a9652835b484329000b7844a6dae40c");
    }
}
