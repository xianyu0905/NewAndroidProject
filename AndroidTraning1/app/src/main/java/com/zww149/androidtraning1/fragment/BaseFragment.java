package com.zww149.androidtraning1.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BaseFragment extends Fragment {
    protected FragmentActivity activity;
    protected String TAG;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
        TAG=getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view;
        if (setLayoutResourceID()==0){
            TextView textView = new TextView(activity);
            textView.setText(TAG);
            view  = textView;
        }else {
            view = inflater.inflate(setLayoutResourceID(),container,false);

        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected void initData() {
    }

    protected void initView(View view) {
    }

    protected int setLayoutResourceID() {

        return 0;
    }
}
