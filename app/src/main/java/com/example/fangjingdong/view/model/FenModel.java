package com.example.fangjingdong.view.model;

import android.util.Log;


import com.example.fangjingdong.view.bean.ZiFenBean;
import com.example.fangjingdong.view.presenter.IFenPre;
import com.example.fangjingdong.view.util.OkHttp3Util_03;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/1/10.
 */

public class FenModel {
    private IFenPre iFenPre;

    public FenModel(IFenPre iFenPre){
        this.iFenPre=iFenPre;

    }

    public void getDateUrl(String s,String cid) {

        Map<String, String> map=new HashMap<>();
        map.put("cid",cid );
        OkHttp3Util_03.doPost(s, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    Gson gson = new Gson();
                    ZiFenBean zifenBean = gson.fromJson(json, ZiFenBean.class);
                    iFenPre.OnSuccess(zifenBean);
                }
            }
        });

    }
}
