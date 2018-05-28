package com.example.fangjingdong.view.model;

import com.example.fangjingdong.view.bean.ZhuCeBean;
import com.example.fangjingdong.view.presenter.IZhuPre;
import com.example.fangjingdong.view.util.OkHttp3Util_03;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/2/2.
 */

public class ZhuCeModel {
    private IZhuPre iZhuPre;

    public ZhuCeModel(IZhuPre iZhuPre){
        this.iZhuPre=iZhuPre;

    }

    public void getDateUrl(String zhuceUrl, String phone, String psw) {
        Map<String, String> map=new HashMap<>();
        map.put("mobile",phone);
        map.put("password",psw);
        OkHttp3Util_03.doPost(zhuceUrl, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        String json = response.body().string();
                        Gson gson = new Gson();
                        ZhuCeBean zhuCeBean = gson.fromJson(json, ZhuCeBean.class);
                        iZhuPre.OnSuccess(zhuCeBean);
                    }
            }
        });
    }
}
