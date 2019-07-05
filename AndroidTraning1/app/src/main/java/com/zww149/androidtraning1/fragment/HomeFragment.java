package com.zww149.androidtraning1.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zww149.androidtraning1.R;
import com.zww149.androidtraning1.activity.NewsDetailActivity;
import com.zww149.androidtraning1.activity.PythonActivity;
import com.zww149.androidtraning1.adapter.HomeAdapter;
import com.zww149.androidtraning1.bean.NewsBean;
import com.zww149.androidtraning1.utils.ConstantUtils;
import com.zww149.androidtraning1.utils.ImageUtils;
import com.zww149.androidtraning1.utils.JsonParseUtils;
import com.zww149.androidtraning1.utils.NetUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class HomeFragment extends BaseFragment {

    private HomeAdapter homeAdapter;
    private Banner banner;
    private List<NewsBean> newsList_AD;
    private List<NewsBean> newsList_news;
    private List<String> ttitle;//轮播图的标题

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        TextView title = view.findViewById(R.id.title);
        title.setText("首页");
        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*，false*/);//传入false表示刷新失败
                getADData();
                getNewsData();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*，false*/);//传入false表示刷新失败
                getNewsData();
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        homeAdapter = new HomeAdapter(null);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));


        View headView = LayoutInflater.from(activity).inflate(R.layout.head_home, null);
        homeAdapter.addHeaderView(headView);

        banner = headView.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                if (path instanceof String) {
                    ImageUtils.setImage(context, (String) path, imageView);
                }
                if (path instanceof Integer) {
                    imageView.setImageResource((Integer) path);
                }
            }
        });
        //设置轮播效果
        banner.setBannerAnimation(Transformer.CubeIn);
        //显示的效果（加了底边的图片标题）
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        ArrayList<String> tt = new ArrayList<>();//轮播图的标题
        ArrayList<Integer> images = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            images.add(R.drawable.pic_item_list_default);
            tt.add("加载失败");
        }

        banner.setBannerTitles(tt);

        banner.setIndicatorGravity(BannerConfig.RIGHT);

        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        View emptyView = LayoutInflater.from(activity).inflate(R.layout.empty_home, null);
        homeAdapter.setEmptyView(emptyView);
        homeAdapter.setHeaderAndEmpty(true);
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(activity, NewsDetailActivity.class);
                intent.putExtra("url", newsList_news.get(position).getNewsUrl());
                startActivity(intent);
            }
        });

        LinearLayout linearLayout = headView.findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PythonActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void initData() {
        super.initData();
        //       getADData();
        //       getNewsData();
    }

    private void getNewsData() {
        NetUtils.getDataAsyn(ConstantUtils.REQUEST_NEWS_URL, new NetUtils.MyCallBack() {
            @Override
            public void onFailure() {
                View emptyView = LayoutInflater.from(activity).inflate(R.layout.empty_home,
                        null);

                homeAdapter.setEmptyView(emptyView);

            }

            @Override
            public void onResponse(final String json) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        newsList_news = JsonParseUtils.getList(NewsBean.class, json);
                        homeAdapter.setNewData(newsList_news);
                    }
                });
            }
        });
    }

    private void getADData() {
        NetUtils.getDataAsyn(ConstantUtils.REQUEST_AD_URL, new NetUtils.MyCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onResponse(final String json) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        newsList_AD = JsonParseUtils.getList(NewsBean.class, json);
                        List<String> images = new ArrayList<>();

                        ttitle = new ArrayList<>();

                        for (NewsBean newsBean : newsList_AD) {
                            images.add(ConstantUtils.WEB_SITE + newsBean.getImg1());

                            ttitle.add(newsBean.getNewsName());

                        }
                        banner.setImages(images);
                        banner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                Intent intent = new Intent(activity, NewsDetailActivity.class);
                                intent.putExtra("url",
                                        newsList_AD.get(position).getNewsUrl());
                                startActivity(intent);
                            }
                        });
                        banner.setBannerTitles(ttitle);
                        banner.start();

                    }
                });


            }
        });

    }

}
