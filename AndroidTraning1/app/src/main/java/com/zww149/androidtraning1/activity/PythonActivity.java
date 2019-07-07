package com.zww149.androidtraning1.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zww149.androidtraning1.R;
import com.zww149.androidtraning1.adapter.PythonAdapter;
import com.zww149.androidtraning1.bean.PythonBean;
import com.zww149.androidtraning1.utils.ConstantUtils;
import com.zww149.androidtraning1.utils.JsonParseUtils;
import com.zww149.androidtraning1.utils.NetUtils;

import java.util.List;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/4 19:25
 */
public class PythonActivity extends HomeAsUpBaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python);

        final TextView titleView = findViewById(R.id.title);
        titleView.setText("python学科");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        final PythonAdapter pythonAdapter = new PythonAdapter(R.layout.item_python);
        recyclerView.setAdapter(pythonAdapter);

        NetUtils.getDataAsyn(ConstantUtils.REQUEST_PYTHON_URL, new NetUtils.MyCallBack() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onResponse(final String json) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<PythonBean> list = JsonParseUtils.getList(PythonBean.class,json);
                        pythonAdapter.setNewData(list);
                    }
                });

            }
        });
    }
}
