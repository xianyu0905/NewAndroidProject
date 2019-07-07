package com.zww149.androidtraning1.activity;

import android.content.Intent;
import android.os.Bundle;

import com.zww149.androidtraning1.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        splash();

    }

    private void splash() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };
        timer.schedule(timerTask,1000);
    }
}
