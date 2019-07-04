package com.zww149.androidtraning1.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zww149.androidtraning1.bean.ConstellationBean;
import com.zww149.androidtraning1.bean.NewsBean;
import com.zww149.androidtraning1.bean.PythonBean;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/2 19:46
 */
public class JsonParseUtils {

    private static Gson gson = new Gson();

    public static List<NewsBean> getNewsList(String json){
        Type listType = new TypeToken<List<NewsBean>>(){}.getType();
        return gson.fromJson(json,listType);
    }

    public static List<NewsBean> getADList(String json){
        Type listType = new TypeToken<List<NewsBean>>(){}.getType();
        return gson.fromJson(json,listType);
    }
     public static List<NewsBean> getPythonList(String json){
        Type listType = new TypeToken<List<PythonBean>>(){}.getType();
        return gson.fromJson(json,listType);
    }
     public static List<NewsBean> getConstellationBeanList(String json){
        Type listType = new TypeToken<List<ConstellationBean>>(){}.getType();
        return gson.fromJson(json,listType);
    }


}
