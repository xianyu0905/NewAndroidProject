package com.zww149.androidtraning1.utils;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/2 19:25
 */
public class ConstantUtils {
    //内网接口
    public static final String WEB_SITE="http://47.101.174.248:8080/topline";

    //首页滑动广告接口
    public static final String REQUEST_AD_URL=WEB_SITE+"/home_ad_list_data.json";

    //首页新闻列表接口
    public static final String REQUEST_NEWS_URL=WEB_SITE+"/home_news_list_data.json";

    //首页python学科列表接口
    public static final String REQUEST_PYTHON_URL=WEB_SITE+"/python_list_data.json";

    //视频列表接口
    public static final String REQUEST_VIDEO_URL=WEB_SITE+"/video_list_data.json";

    //星座界面接口
    public static final String REQUEST_CONSTELLATION_URL=WEB_SITE+"/constellation_data.json";

    //星座选择界面接口
    public static final String REQUEST_CHOOSE_CONSTELLATION_LIST_URL=
            WEB_SITE+"/choose_constellation_list_data.json";

}
