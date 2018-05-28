package com.example.fangjingdong.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fangjingdong.R;
import com.example.fangjingdong.view.util.CommonUtils;
import com.example.fangjingdong.view.util.GlideImgManager;

public class Zhanghushezhi extends AppCompatActivity implements View.OnClickListener {

    private ImageView zhanghushezhi_image_back;
    private ImageView zhanghushezhi_icon;
    private TextView zhanghushezhi_name;
    private TextView tuichu_login;
    private RelativeLayout zhanghushezhi_user;
    private ImageView setting_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhanghushezhi);
        zhanghushezhi_image_back = (ImageView) findViewById(R.id.zhanghushezhi_image_back);
        zhanghushezhi_icon = (ImageView) findViewById(R.id.zhanghushezhi_icon);
        zhanghushezhi_name = (TextView) findViewById(R.id.zhanghushezhi_name);
        tuichu_login = (TextView) findViewById(R.id.tuichu_login);
        zhanghushezhi_user = (RelativeLayout) findViewById(R.id.zhanghushezhi_user);
        zhanghushezhi_image_back.setOnClickListener(this);
        tuichu_login.setOnClickListener(this);
        zhanghushezhi_user.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isLogin = CommonUtils.getBoolean("isLogin");

        if (isLogin) {
            if ( "".equals(CommonUtils.getString("iconUrl"))  || "null".equals(CommonUtils.getString("iconUrl"))){
                //显示默认头像
                zhanghushezhi_icon.setImageResource(R.drawable.user);
            }else {

                //1.加载一下头像显示...判断一下是否有头像的路径,,,没有则显示默认的头像
                GlideImgManager.glideLoader(Zhanghushezhi.this, CommonUtils.getString("iconUrl"), R.drawable.user, R.drawable.user, zhanghushezhi_icon, 0);
            }
            //2.先试一下用户名
            zhanghushezhi_name.setText(CommonUtils.getString("name"));

        }else {
            //显示默认头像
            zhanghushezhi_icon.setImageResource(R.drawable.user);
            //用户名显示 登录/注册 >
            zhanghushezhi_name.setText("登录/注册 >");

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhanghushezhi_image_back:

                finish();

                break;
            case R.id.tuichu_login://退出登录...实际上是请求退出的接口..在这把登录成功保存的信息清空..结束当前页面的显示

                CommonUtils.clearSp("isLogin");
                CommonUtils.clearSp("uid");
                CommonUtils.clearSp("name");
                CommonUtils.clearSp("iconUrl");

                finish();
                break;
            case R.id.zhanghushezhi_user://进入个人中心
                Intent intent = new Intent(Zhanghushezhi.this, Gerenzhongxin.class);
                startActivity(intent);
                break;
        }
    }
}
