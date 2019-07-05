package com.zww149.androidtraning1.utils;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zww149.androidtraning1.bean.ConstellationBean;
import com.zww149.androidtraning1.bean.NewsBean;
import com.zww149.androidtraning1.bean.PythonBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/2 19:46
 */
public class JsonParseUtils {

    private static Gson gson = new Gson();

    /**
     * 第二个优化
     */
    //泛型
    public static <T>List<T> getList(Class<T> t,String json){
        Type listType = new MyParameterizedType(t);
        return gson.fromJson(json,listType);
    }



    private static class MyParameterizedType implements ParameterizedType {
        Class raw;

        public MyParameterizedType(Class raw){
            this.raw = raw;
        }
        @NonNull

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{raw};
        }

        @NonNull
        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

   /* public static List<NewsBean> getADList(String json){
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
    }*/


}
