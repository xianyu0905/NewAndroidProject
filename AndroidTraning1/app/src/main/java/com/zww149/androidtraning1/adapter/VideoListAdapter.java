package com.zww149.androidtraning1.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zww149.androidtraning1.R;
import com.zww149.androidtraning1.bean.VideoBean;


import java.util.List;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/8 15:23
 */
public class VideoListAdapter extends BaseQuickAdapter<VideoBean.VideoDetailListBean,
        BaseViewHolder> {


    public VideoListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoBean.VideoDetailListBean item) {
        helper.setText(R.id.textView,item.getVideo_name());
    }


}
