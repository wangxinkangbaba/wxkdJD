package com.example.fangjingdong.view.model;

import com.example.fangjingdong.view.bean.LoginBean;
import com.example.fangjingdong.view.presenter.ILoginPre;
import com.example.fangjingdong.view.util.CommonUtils;
import com.example.fangjingdong.view.util.JieKou;
import com.example.fangjingdong.view.util.OkHttp3Util_03;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/1/31.
 */

public class LoginModel {
    private ILoginPre iLoginPre;

    public LoginModel(ILoginPre iLoginPre){
        this.iLoginPre=iLoginPre;

    }

    public void getDateUrl(String loginUrl, String phone, String psw) {
        Map<String, String> map=new HashMap<>();
        map.put("mobile",phone);
        map.put("password",psw);
        OkHttp3Util_03.doPost(loginUrl, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        String json = response.body().string();
                        Gson gson = new Gson();
                        final LoginBean loginBean = gson.fromJson(json, LoginBean.class);
                        CommonUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                iLoginPre.OnSuccess(loginBean);
                            }
                        });
                    }
            }
        });
    }

    public void getLoginByQQ(String phone, String pwd, final String ni_cheng, final String iconurl) {
        Map<String, String> params=new HashMap<>();
        params.put("mobile",phone);
        params.put("password",pwd);
        OkHttp3Util_03.doPost(JieKou.LOGIN_URL, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        String json = response.body().string();
                        //解析
                        final LoginBean loginBean = new Gson().fromJson(json,LoginBean.class);

                        //回调
                        CommonUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                iLoginPre.onSuccessByQQ(loginBean,ni_cheng,iconurl);
                            }
                        });
                    }
            }
        });
    }
}
