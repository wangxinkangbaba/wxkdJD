package com.example.fangjingdong.view.model;

import com.example.fangjingdong.view.bean.AddCartBean;
import com.example.fangjingdong.view.presenter.IAddCartPre;
import com.example.fangjingdong.view.util.OkHttp3Util_03;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/2/1.
 */

public class AddCartModel {
    private  IAddCartPre iAddCartPre;

    public AddCartModel(IAddCartPre iAddCartPre){
        this.iAddCartPre=iAddCartPre;

    }

    public void getDateUrl(String addCart, String uid, String pid) {
        Map<String, String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("pid",pid);
        map.put("source","android");
        OkHttp3Util_03.doPost(addCart, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        String json = response.body().string();
                        Gson gson = new Gson();
                        AddCartBean addCartBean = gson.fromJson(json, AddCartBean.class);
                        iAddCartPre.OnSuccess(addCartBean);
                    }
            }
        });
    }
}
