package com.zww149.androidtraning1.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import com.zww149.androidtraning1.R;
import com.zww149.androidtraning1.activity.BaseActivity;
import com.zww149.androidtraning1.adapter.MyFragmentStatePagerAdapter;
import com.zww149.androidtraning1.fragment.BaseFragment;
import com.zww149.androidtraning1.fragment.ChartFragment;
import com.zww149.androidtraning1.fragment.HomeFragment;
import com.zww149.androidtraning1.fragment.MeFragment;
import com.zww149.androidtraning1.fragment.VideoFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private BottomNavigationView navView;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_chart:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_video:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_me:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        ArrayList<BaseFragment> data = new ArrayList<>();
        data.add(new HomeFragment());
        data.add(new ChartFragment());
        data.add(new VideoFragment());
        data.add(new MeFragment());
        viewPager.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager(),data));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                navView.getMenu().getItem(position).setChecked(true);
            }
        });

        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
