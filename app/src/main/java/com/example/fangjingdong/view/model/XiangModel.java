package com.example.fangjingdong.view.model;


import android.util.Log;

import com.example.fangjingdong.view.bean.XiangBean;
import com.example.fangjingdong.view.presenter.IXiangpre;
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
 * Created by ASUS on 2018/1/9.
 */

public class XiangModel {
    private IXiangpre iXiangpre;

    public XiangModel(IXiangpre iXiangpre){
        this.iXiangpre=iXiangpre;

    }

    public void getDateUrl(String pid) {
        Map<String, String> map=new HashMap<>();
        map.put("pid",pid);
        map.put("source","android");
        OkHttp3Util_03.doPost(JieKou.DETAIL_URL, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        String json = response.body().string();
                        Gson gson = new Gson();
                        XiangBean xiangBean = gson.fromJson(json, XiangBean.class);
                        iXiangpre.OnSuccess(xiangBean);
                    }
            }
        });
    }
}
