package com.example.fangjingdong.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.ILoginMain;
import com.example.fangjingdong.view.bean.LoginBean;
import com.example.fangjingdong.view.presenter.Loginpre;
import com.example.fangjingdong.view.util.CommonUtils;
import com.example.fangjingdong.view.util.JieKou;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import java.util.Map;

public class Login extends AppCompatActivity implements ILoginMain, View.OnClickListener {

    private EditText login_phone;
    private EditText login_psw;
    private Loginpre loginpre;
    private TextView login_zhuce;
    private ImageView login_by_wechat;
    private ImageView login_by_qq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_phone = (EditText) findViewById(R.id.login_phone);
        login_psw = (EditText) findViewById(R.id.login_psw);
        login_zhuce = (TextView) findViewById(R.id.login_zhuce);
        login_by_wechat = (ImageView) findViewById(R.id.login_by_wechat);
        login_by_qq = (ImageView) findViewById(R.id.login_by_qq);
        login_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ZhuCe.class);
                startActivity(intent);
            }
        });
        loginpre = new Loginpre(this);
        login_by_wechat.setOnClickListener(this);
        login_by_qq.setOnClickListener(this);
    }
    public void login(View view){
        String phone = login_phone.getText().toString();
        String psw = login_psw.getText().toString();
        loginpre.getDate(JieKou.LOGIN_URL,phone,psw);
    }

    @Override
    public void ILoginSuccess(LoginBean loginBean) {
        if ("0".equals(loginBean.getCode())) {//登录成功
            //登录成功之后需要做:.....保存状态true...
            CommonUtils.putBoolean("isLogin",true);
            CommonUtils.saveString("uid", String.valueOf(loginBean.getData().getUid()));
            CommonUtils.saveString("name",loginBean.getData().getUsername());
            CommonUtils.saveString("iconUrl", (String) loginBean.getData().getIcon());

            //结束当前界面
            finish();
        }
    }

    @Override
    public void getLoginSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl) {
        if ("0".equals(loginBean.getCode())) {//登录成功
            //登录成功之后需要做:.....保存状态true...
            CommonUtils.putBoolean("isLogin",true);
            CommonUtils.saveString("uid", String.valueOf(loginBean.getData().getUid()));
            CommonUtils.saveString("name",ni_cheng);
            CommonUtils.saveString("iconUrl",iconurl);

            //结束当前界面
            finish();
        }
    }

    public void fan(View view){
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
             case R.id.login_by_wechat://微信登录

                UMShareAPI.get(Login.this).getPlatformInfo(Login.this, SHARE_MEDIA.WEIXIN, authListener);

                break;
            case R.id.login_by_qq://qq登录

                UMShareAPI.get(Login.this).getPlatformInfo(Login.this, SHARE_MEDIA.QQ, authListener);

                break;
        }
    }
    /**
     * 授权的监听事件
     */
    private UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {


            String qq_uid = data.get("uid");
            String ni_cheng = data.get("name");
            String iconurl = data.get("iconurl");

            //实际上是根据这些qq提供的信息,去服务器拿到分配的临时账号,登录进京东的服务器
            //授权成功拿到信息模拟登录
            Log.i("----",ni_cheng+"--"+iconurl);

            loginpre.getLoginByQQ("18735944346","123456",ni_cheng,iconurl);


        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(Login.this, "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(Login.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
}
