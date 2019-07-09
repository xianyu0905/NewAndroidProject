package com.zww149.androidtraning1.fragment;

import android.view.View;
import android.widget.TextView;




import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zww149.androidtraning1.R;
import com.zww149.androidtraning1.activity.VideoDetailActivity;

import com.zww149.androidtraning1.adapter.VideoListAdapter;
import com.zww149.androidtraning1.bean.VideoBean;


import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VideoListFragment extends BaseFragment {
    private String url0 ="http://video.7k.cn/app_video/20171202/6c8cf3ea/v.m3u8.mp4";
    private String url1 ="http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_video_list;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        VideoListAdapter videoListAdapter = new VideoListAdapter(R.layout.item_video_list);
        recyclerView.setAdapter(videoListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        //todo 视频bean下的内部类

        List<VideoBean.VideoDetailListBean> videoList =
                activity.getIntent().getParcelableArrayListExtra("videoList");

        videoListAdapter.setNewData(videoList);
        videoListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //todo 复制视频地址
                if(position==0){
                    ((VideoDetailActivity)activity).playNewUrl(url0);
                }else{
                    ((VideoDetailActivity)activity).playNewUrl(url1);
                }
            }
        });

    }
}