package com.jyw.languagetest;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;

public class MyApplication extends Application {
    private static final String TAG = "jyw1";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: 应用启动了...");
        MultiLanguageUtil.init(this);

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("lang", MODE_PRIVATE);
            int index = sharedPreferences.getInt("lang",0);
            if(index!=0){
                Log.d(TAG, "onCreate: application index=="+index);
                MultiLanguageUtil.getInstance().updateLanguage(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: 配置更新了....");
    }
}
