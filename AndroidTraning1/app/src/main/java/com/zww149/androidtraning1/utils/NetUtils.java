package com.zww149.androidtraning1.utils;

import com.zww149.androidtraning1.bean.NewsBean;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/4 11:16
 */
public class NetUtils {

    public interface MyCallBack{
        void onFailure();
        void onResponse(String json);
    }

    public static void getDataAsyn(String url,final MyCallBack myCallBack){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                myCallBack.onFailure();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response)
                    throws IOException {
                ResponseBody body = response.body();
                if (body != null) {
                    final String json = body.string();
                    myCallBack.onResponse(json);



                }
            }
        });
    }
}
