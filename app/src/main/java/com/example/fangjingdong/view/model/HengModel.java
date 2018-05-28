package com.example.fangjingdong.view.model;

import com.example.fangjingdong.view.bean.HengBean;
import com.example.fangjingdong.view.presenter.IHengpre;
import com.example.fangjingdong.view.util.OkHttp3Util_03;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/1/24.
 */

public class HengModel {
    private IHengpre iHengpre;

    public HengModel(IHengpre iHengpre){
        this.iHengpre=iHengpre;

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
                    HengBean hengBean = gson.fromJson(json, HengBean.class);
                    iHengpre.OnSuccess(hengBean);
                }
            }
        });
    }
}
