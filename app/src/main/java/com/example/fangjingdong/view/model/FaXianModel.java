package com.example.fangjingdong.view.model;

import com.example.fangjingdong.view.bean.FaXianBean;
import com.example.fangjingdong.view.presenter.FaXianpre;
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
 * Created by ASUS on 2018/3/23.
 */

public class FaXianModel {
    private FaXianpre faXianpre;

    public FaXianModel(FaXianpre faXianpre) {
        this.faXianpre=faXianpre;

    }

    public void getDateUrl(String s, String uid) {
        Map<String, String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("type","1");
        map.put("source","android");
        OkHttp3Util_03.doPost(s, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        final String json = response.body().string();
                        CommonUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                Gson gson = new Gson();
                                FaXianBean faXianBean = gson.fromJson(json, FaXianBean.class);
                                faXianpre.OnSucess(faXianBean);
                            }
                        });
                    }
            }
        });
    }
}
