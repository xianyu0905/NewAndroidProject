package com.zww149.androidtraning1.fragment;




import android.view.View;
import android.widget.TextView;

import com.zww149.androidtraning1.R;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/8 21:28
 */
public class VideoIntroFragment extends BaseFragment {
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_video_intro;

    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        TextView textView = view.findViewById(R.id.textView);
        String videoIntro = activity.getIntent().getStringExtra("videoIntro");
        textView.setText(videoIntro);
    }
}
