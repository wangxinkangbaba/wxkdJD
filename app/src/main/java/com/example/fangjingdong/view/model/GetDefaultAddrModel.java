package com.example.fangjingdong.view.model;

import com.example.fangjingdong.view.bean.DefaultAddrBean;
import com.example.fangjingdong.view.presenter.GetDefaultAddrPresenter;
import com.example.fangjingdong.view.presenter.GetDefaultAddrPresenterInter;
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
 * Created by ASUS on 2018/3/7.
 */

public class GetDefaultAddrModel {


    private GetDefaultAddrPresenterInter getDefaultAddrPresenterInter;

    public GetDefaultAddrModel(GetDefaultAddrPresenterInter getDefaultAddrPresenterInter) {
        this.getDefaultAddrPresenterInter = getDefaultAddrPresenterInter;
    }

    public void getDefaultAddr(String getDefaultAddrUrl, String uid) {
        Map<String, String> map=new HashMap<>();
        map.put("uid",uid);
        OkHttp3Util_03.doPost(getDefaultAddrUrl, map, new Callback() {
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
                                if ("{}".equals(json)) {//没有地址数据
                                    getDefaultAddrPresenterInter.onGetDefaultAddrEmpty();

                                }else {
                                    DefaultAddrBean defaultAddrBean = new Gson().fromJson(json,DefaultAddrBean.class);

                                    getDefaultAddrPresenterInter.onGetDefaultAddrSuccess(defaultAddrBean);

                                }
                            }
                        });
                    }
            }
        });
    }
}
