package com.system.myproject;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 创建人： zhoudingwen
 * 创建时间：2018/4/2
 */

public class BaseMainApp extends MultiDexApplication {
    private static BaseMainApp mainApp;
    @Override
    public void onCreate() {
        super.onCreate();
        mainApp=this;

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    public static Context getContext(){
        return mainApp;
    }
}
