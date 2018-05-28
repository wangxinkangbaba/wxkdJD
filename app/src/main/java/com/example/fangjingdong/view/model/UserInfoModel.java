package com.example.fangjingdong.view.model;


import com.example.fangjingdong.view.bean.UserInfoBean;
import com.example.fangjingdong.view.presenter.UserInfoPresenterInter;
import com.example.fangjingdong.view.util.CommonUtils;
import com.example.fangjingdong.view.util.OkHttp3Util_03;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Dash on 2018/2/23.
 */
public class UserInfoModel {

    private UserInfoPresenterInter userInfoPresenterInter;

    public UserInfoModel(UserInfoPresenterInter userInfoPresenterInter) {
        this.userInfoPresenterInter = userInfoPresenterInter;
    }

    public void getUserInfo(String userInfoUrl, String uid) {

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        OkHttp3Util_03.doPost(userInfoUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    String json = response.body().string();

                    final UserInfoBean userInfoBean = new Gson().fromJson(json,UserInfoBean.class);

                    CommonUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            userInfoPresenterInter.onUserInfoSUccess(userInfoBean);
                        }
                    });

                }

            }
        });

    }
}
