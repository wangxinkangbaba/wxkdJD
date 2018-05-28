package com.example.fangjingdong.view.model;

import com.example.fangjingdong.view.bean.CreateOrderBean;
import com.example.fangjingdong.view.presenter.CreateOrderPresenterInter;
import com.example.fangjingdong.view.util.OkHttp3Util_03;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/3/2.
 */

public class CreatDingDanModel {
    private CreateOrderPresenterInter createOrderPresenterInter;

    public CreatDingDanModel(CreateOrderPresenterInter createOrderPresenterInter){
        this.createOrderPresenterInter=createOrderPresenterInter;

    }

    public void getDateUrl(String createOrderUrl, String uid, double price) {
        Map<String, String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("price", String.valueOf(price));
        OkHttp3Util_03.doPost(createOrderUrl, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();

                    CreateOrderBean createOrderBean = new Gson().fromJson(json,CreateOrderBean.class);

                    createOrderPresenterInter.OnSuccess(createOrderBean);

                }

            }
        });
    }
}
