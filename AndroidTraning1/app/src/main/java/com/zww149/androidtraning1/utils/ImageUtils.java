package com.zww149.androidtraning1.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/4 18:38
 */
public class ImageUtils {

    public static void setImage(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }
}
