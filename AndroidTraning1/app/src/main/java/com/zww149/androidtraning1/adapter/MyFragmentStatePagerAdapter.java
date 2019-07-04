package com.zww149.androidtraning1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zww149.androidtraning1.activity.BaseActivity;
import com.zww149.androidtraning1.fragment.BaseFragment;

import java.util.ArrayList;

public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<BaseFragment> data;
    public MyFragmentStatePagerAdapter(FragmentManager fm,ArrayList<BaseFragment> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int i) {
        return data.get(i);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
