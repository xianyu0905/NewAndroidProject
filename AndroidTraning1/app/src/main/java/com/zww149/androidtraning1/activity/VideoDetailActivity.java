package com.zww149.androidtraning1.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.tabs.TabLayout;
import com.shuyu.gsyvideoplayer.GSYVideoADManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.zww149.androidtraning1.R;

import com.zww149.androidtraning1.adapter.MyFragmentStatePagerAdapter;
import com.zww149.androidtraning1.fragment.BaseFragment;
import com.zww149.androidtraning1.fragment.VideoIntroFragment;
import com.zww149.androidtraning1.fragment.VideoListFragment;
import com.zww149.androidtraning1.utils.ConstantUtils;
import com.zww149.androidtraning1.utils.ImageUtils;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;


/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/8 15:41
 */
public class VideoDetailActivity extends HomeAsUpBaseActivity {

    private StandardGSYVideoPlayer detailPlayer;
    private String url = "http://video.7k.cn/app_video/20171202/6c8cf3ea/v.m3u8.mp4";

    private boolean isPlay;
    private boolean isPause;

    private OrientationUtils orientationUtils;
    private GSYVideoOptionBuilder gsyVideoOption;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        detailPlayer = findViewById(R.id.detail_play);

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Intent intent = getIntent();
        String videoImage = intent.getStringExtra("videoImage");
        ImageUtils.setImage(this, ConstantUtils.WEB_SITE + videoImage, imageView);

        //增加title
        TextView titleView = findViewById(R.id.title);
        String videoName = intent.getStringExtra("videoName");
        titleView.setText(videoName);
        initDetail();

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        gsyVideoOption = new GSYVideoOptionBuilder();
        gsyVideoOption.setThumbImageView(imageView)
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setAutoFullWithSize(true)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setUrl(url)
                .setCacheWithPlay(false)
                .setVideoTitle("测试视频")
                .setVideoAllCallBack(new GSYSampleCallBack() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        //开始播放了才能旋转和全屏
                        orientationUtils.setEnable(true);
                        isPlay = true;
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo();
                        }
                    }
                }).setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        }).build(detailPlayer);

        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();
                //第一个true是否需要隐藏actionbar,第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(VideoDetailActivity.this,
                        true, true);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (GSYVideoADManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        detailPlayer.getCurrentPlayer().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        detailPlayer.getCurrentPlayer().onVideoResume(false);
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPlay) {
            detailPlayer.getCurrentPlayer().release();
        }
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (isPlay && !isPause) {
            detailPlayer.onConfigurationChanged(this, newConfig, orientationUtils,
                    true, true);
        }
    }

    private void initDetail() {
        ViewPager viewPager = findViewById(R.id.viewPager);
        ArrayList<BaseFragment> data = new ArrayList<>();
        data.add(new VideoIntroFragment());
        data.add(new VideoListFragment());
        viewPager.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager(),data){
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return "视频简介";
                    case 1:
                        return "视频目录";
                }
                return super.getPageTitle(position);
            }
        });

        TabLayout tabLayout = findViewById(R.id.tableLayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void playNewUrl(String url){
        gsyVideoOption.setUrl(url).build(detailPlayer);
        detailPlayer.startPlayLogic();
    }
}
