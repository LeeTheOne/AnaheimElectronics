package com.anaheimelectronics.launch;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class AEApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //配置LeakCanary
        setupLeakCanary();
    }

    /**
     * 配置LeakCanary
     */
    private void setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
