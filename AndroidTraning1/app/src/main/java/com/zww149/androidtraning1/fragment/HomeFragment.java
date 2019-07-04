package com.zww149.androidtraning1.fragment;


import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zww149.androidtraning1.R;
import com.zww149.androidtraning1.adapter.HomeAdapter;
import com.zww149.androidtraning1.bean.NewsBean;
import com.zww149.androidtraning1.utils.ConstantUtils;
import com.zww149.androidtraning1.utils.JsonParseUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class HomeFragment extends BaseFragment {

    private HomeAdapter homeAdapter;
    private MyHandle myHandle;

    class MyHandle extends Handler {

        public static final int NEWS_OK=1;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case NEWS_OK:
                    List<NewsBean> newsList = JsonParseUtils.getNewsList((String) msg.obj);
                    homeAdapter.setNewData(newsList);
            }
        }
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
//        TextView title = view.findViewById(R.id.title);
//        title.setText("首页");
        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*，false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*，false*/);//传入false表示刷新失败
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        homeAdapter = new HomeAdapter(null);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        myHandle = new MyHandle();
    }

    @Override
    protected void initData() {
        super.initData();
        getADData();
        getNewsData();
    }

    private void getNewsData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(ConstantUtils.REQUEST_NEWS_URL)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response)
                    throws IOException {
                ResponseBody body = response.body();
                if (body !=null){
                    String json = body.string();
                    Log.i(TAG,json);
                    Message message = Message.obtain();
                    message.what = MyHandle.NEWS_OK;
                    message.obj = json;
                    myHandle.sendMessage(message);
                }
            }
        });
    }

    private void getADData() {

    }


}
