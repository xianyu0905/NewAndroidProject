package com.zww149.androidtraning1.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zww149.androidtraning1.R;
import com.zww149.androidtraning1.bean.NewsBean;
import com.zww149.androidtraning1.utils.ConstantUtils;
import com.zww149.androidtraning1.utils.ImageUtils;

import java.util.List;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/2 21:16
 */
public class HomeAdapter extends BaseMultiItemQuickAdapter<NewsBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeAdapter(List<NewsBean> data) {
        super(data);
        addItemType(1, R.layout.item_news1);
        addItemType(2, R.layout.item_news2);
    }


    protected void convert(BaseViewHolder helper, NewsBean item) {
        switch (helper.getItemViewType()) {
            case 1:
                helper.setText(R.id.textView, item.getNewsName());
                helper.setText(R.id.textView2, item.getNewsTypeName());
                ImageView imageView = helper.getView(R.id.imageView);

                ImageUtils.setImage(helper.itemView.getContext(),
                        ConstantUtils.WEB_SITE + item.getImg1(),imageView);
                break;
            case 2:
                helper.setText(R.id.textView, item.getNewsName());
                helper.setText(R.id.textView2, item.getNewsTypeName());
                ImageView imageView1 = helper.getView(R.id.imageView);
                ImageView imageView2 = helper.getView(R.id.imageView2);
                ImageView imageView3 = helper.getView(R.id.imageView3);
                /*Glide.with(helper.itemView.getContext())
                        .load(ConstantUtils.WEB_SITE + item.getImg1()).into(imageView1);
                Glide.with(helper.itemView.getContext())
                        .load(ConstantUtils.WEB_SITE + item.getImg2()).into(imageView2);
                Glide.with(helper.itemView.getContext())
                        .load(ConstantUtils.WEB_SITE + item.getImg3()).into(imageView3);*/

                ImageUtils.setImage(helper.itemView.getContext(),
                        ConstantUtils.WEB_SITE + item.getImg1(),imageView1);
                ImageUtils.setImage(helper.itemView.getContext(),
                        ConstantUtils.WEB_SITE + item.getImg2(),imageView2);
                ImageUtils.setImage(helper.itemView.getContext(),
                        ConstantUtils.WEB_SITE + item.getImg3(),imageView3);
                break;

        }

    }


}
