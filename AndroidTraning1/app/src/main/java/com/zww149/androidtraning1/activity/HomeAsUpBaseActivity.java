package com.zww149.androidtraning1.activity;




import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zww149.androidtraning1.R;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/4 19:34
 */
public class HomeAsUpBaseActivity extends BaseActivity {

    @Override
    protected void onStart() {
        super.onStart();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
