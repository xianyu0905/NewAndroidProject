package com.zww149.androidtraning1.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    protected String TAG;
    private long exitTime;

    /**
     * 定义个广播
     *
     * @param savedInstanceState
     */
    private BroadcastReceiver exitBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        TAG = getClass().getSimpleName();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //过滤器
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.zww149.androidtraning1.activity.exit");
        registerReceiver(exitBroadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(exitBroadcastReceiver);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
//                finish();
                Intent intent = new Intent();
                intent.setAction("com.zww149.androidtraning1.activity.exit");
                sendBroadcast(intent);
//                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
