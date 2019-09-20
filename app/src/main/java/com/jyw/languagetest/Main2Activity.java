package com.jyw.languagetest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Process;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class Main2Activity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(newBase);
//    }

    @Override
    public void onClick(View view) {
        final String[] datas = new String[]{"中文", "西班牙语"};

        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setSingleChoiceItems(datas, 0, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index) {
                        Toast.makeText(Main2Activity.this, "您选择了" + datas[index], Toast.LENGTH_SHORT).show();

                        if (datas[index].equals("中文")) {
                            MultiLanguageUtil.getInstance().updateLanguage(LanguageType.LANGUAGE_CHINESE_SIMPLIFIED);
                            saveLang("lang",LanguageType.LANGUAGE_CHINESE_SIMPLIFIED);
                        } else {

                            MultiLanguageUtil.getInstance().updateLanguage(LanguageType.LANGUAGE_ES);
                            saveLang("lang",LanguageType.LANGUAGE_ES);
                        }

                        Process.killProcess(Process.myPid());

                    }
                })
                .create();

        dialog.show();
    }

    private void saveLang(String key, int val) {
        SharedPreferences sharedPreferences = getSharedPreferences("lang", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key,val);
        edit.commit();
    }


}
