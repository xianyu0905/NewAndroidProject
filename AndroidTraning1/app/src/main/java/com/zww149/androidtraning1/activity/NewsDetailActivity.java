package com.zww149.androidtraning1.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;
import com.zww149.androidtraning1.R;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/4 19:22
 */
public class NewsDetailActivity extends BaseActivity {
    private AgentWeb mAgentWeb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);

        final TextView titleView = findViewById(R.id.title);

        String url = getIntent().getStringExtra("url");
        LinearLayout linearLayout = findViewById(R.id.linearLayout);

        mAgentWeb = AgentWeb.with(this).setAgentWebParent(linearLayout,
                new LinearLayout.LayoutParams(-1,-1))
                .useDefaultIndicator()
                .setWebChromeClient(new WebChromeClient(){
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                        titleView.setText(title);
                    }
                })
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode,event)){
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
