package com.zww149.androidtraning1.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zww149.androidtraning1.R;
import com.zww149.androidtraning1.bean.PythonBean;

import java.util.List;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/5 9:11
 */
public class PythonAdapter extends BaseQuickAdapter<PythonBean, BaseViewHolder> {


    public PythonAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PythonBean item) {
        helper.setText(R.id.textView, item.getAddress());
        helper.setText(R.id.textView2, item.getContent());

    }

}
