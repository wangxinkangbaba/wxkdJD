package com.example.fangjingdong.view.model;

import android.util.Log;

import com.example.fangjingdong.view.bean.GoodsBean;
import com.example.fangjingdong.view.presenter.IGoodspre;
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
 * Created by ASUS on 2018/1/26.
 */

public class GoodsModel {
    private  IGoodspre iGoodspre;

    public GoodsModel(IGoodspre iGoodspre){
        this.iGoodspre=iGoodspre;

    }

    public void getDateUrl(String keywords,int page) {
        Map<String, String> map=new HashMap<>();
        map.put("keywords",keywords);
        map.put("page",page+"");
        map.put("source","android");
        OkHttp3Util_03.doPost(JieKou.SEARTCH_URL, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    Gson gson = new Gson();
                    GoodsBean goodsBean = gson.fromJson(json, GoodsBean.class);
                    Log.d("qqqqqq",goodsBean.toString()+ "onResponse: ");
                    iGoodspre.OnSuccess(goodsBean);
                }
            }
        });
    }
}
