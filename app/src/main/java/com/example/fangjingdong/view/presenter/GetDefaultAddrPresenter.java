package com.example.fangjingdong.view.presenter;

import com.example.fangjingdong.view.IView.DefaultAddrInter;
import com.example.fangjingdong.view.bean.DefaultAddrBean;
import com.example.fangjingdong.view.model.GetDefaultAddrModel;

/**
 * Created by ASUS on 2018/3/7.
 */

public class GetDefaultAddrPresenter implements GetDefaultAddrPresenterInter {
    private DefaultAddrInter defaultAddrInter;
    private GetDefaultAddrModel getDefaultAddrModel;

    public GetDefaultAddrPresenter(DefaultAddrInter defaultAddrInter) {
        this.defaultAddrInter = defaultAddrInter;
        getDefaultAddrModel = new GetDefaultAddrModel(this);
    }

    public void getDefaultAddr(String getDefaultAddrUrl, String uid) {
        getDefaultAddrModel.getDefaultAddr(getDefaultAddrUrl,uid);
    }

    @Override
    public void onGetDefaultAddrSuccess(DefaultAddrBean defaultAddrBean) {
        defaultAddrInter.onGetDefaultAddrSuccess(defaultAddrBean);
    }

    @Override
    public void onGetDefaultAddrEmpty() {
        defaultAddrInter.onGetDefaultAddrEmpty();
    }
}
