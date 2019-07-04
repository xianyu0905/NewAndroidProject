package com.zww149.androidtraning1.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zww149.androidtraning1.R;


/**
 * @author zhuww
 * @description: 149
 * @date :2019/6/25 20:08
 */
public class TitleBar extends LinearLayout {

    private final Context context;
    private View search;
    private View game;
    private View iv_history;

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        search = getChildAt(1);
        game = getChildAt(2);
        iv_history = getChildAt(3);
        //设置点击事件
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        search.setOnClickListener(myOnClickListener);
        game.setOnClickListener(myOnClickListener);
        iv_history.setOnClickListener(myOnClickListener);
    }

    class MyOnClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_seache:
                    Toast.makeText(context,"搜索",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rl_game:
                    Toast.makeText(context,"游戏",Toast.LENGTH_SHORT).show();

                    break;
                case R.id.iv_history:
                    Toast.makeText(context,"播放历史",Toast.LENGTH_SHORT).show();

                    break;
                default:
                    break;
            }
        }
    }
}
