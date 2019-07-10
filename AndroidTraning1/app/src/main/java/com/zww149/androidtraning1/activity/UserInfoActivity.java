package com.zww149.androidtraning1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zww149.androidtraning1.R;
import com.zww149.androidtraning1.adapter.PythonAdapter;
import com.zww149.androidtraning1.bean.PythonBean;
import com.zww149.androidtraning1.bean.User;
import com.zww149.androidtraning1.utils.ConstantUtils;
import com.zww149.androidtraning1.utils.JsonParseUtils;
import com.zww149.androidtraning1.utils.NetUtils;

import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * @author zhuww
 * @description: 149
 * @date :2019/7/4 19:25
 */
public class UserInfoActivity extends HomeAsUpBaseActivity {


    private TextView textView_userName;
    private TextView textView_nickName;
    private TextView textView_sex;
    private TextView textView_email;
    private TextView textView_info;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python);

        final TextView titleView = findViewById(R.id.title);
        titleView.setText("个人信息");
        textView_userName = findViewById(R.id.textView_userName);
        textView_nickName = findViewById(R.id.textView_nickName);
        textView_sex=findViewById(R.id.textView_sex);
        textView_email=findViewById(R.id.textView_email);
        textView_info=findViewById(R.id.textView_info);
        if (BmobUser.isLogin()){
            User user = BmobUser.getCurrentUser(User.class);
            textView_userName.setText(user.getUsername());
            textView_nickName.setText(user.getNickName());
            textView_sex.setText(user.isSex()?"男":"女");
            textView_email.setText(user.getEmail());
            textView_info.setText(user.getInfo());
        }

    }
}
