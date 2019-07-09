package com.zww149.androidtraning1.adapter;


import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zww149.androidtraning1.R;
import com.zww149.androidtraning1.bean.VideoBean;
import com.zww149.androidtraning1.utils.ConstantUtils;
import com.zww149.androidtraning1.utils.ImageUtils;


public class VideoAdapter extends BaseQuickAdapter<VideoBean, BaseViewHolder> {
    public VideoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoBean item) {
        //todo 添加路径
    ImageUtils.setImage(helper.itemView.getContext(), ConstantUtils.WEB_SITE+item.getImg(),
                (ImageView) helper.getView(R.id.imageView));
    }
}
