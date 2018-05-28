package com.example.fangjingdong.view.model;

import android.util.Log;

import com.example.fangjingdong.view.bean.BannerBean;
import com.example.fangjingdong.view.presenter.IBannerpre;
import com.example.fangjingdong.view.util.OkHttp3Util_03;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/1/23.
 */

public class BannerModel {
    private IBannerpre iBannerpre;

    public BannerModel(IBannerpre iBannerpre){
        this.iBannerpre=iBannerpre;

    }

    public void getDateUrl(String s) {
        Map<String, String> map=new HashMap<>();
        OkHttp3Util_03.doPost(s, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        String json = response.body().string();
                        Gson gson = new Gson();
                        BannerBean bannerBean = gson.fromJson(json, BannerBean.class);

                        iBannerpre.OnSuccess(bannerBean);
                    }
            }
        });
    }
}
