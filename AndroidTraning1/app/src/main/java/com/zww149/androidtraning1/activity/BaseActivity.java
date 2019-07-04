package com.zww149.androidtraning1.activity;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    protected String TAG;
    private long exitTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        TAG = getClass().getSimpleName();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis()-exitTime>2000){
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT);
                exitTime = System.currentTimeMillis();
            }else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
