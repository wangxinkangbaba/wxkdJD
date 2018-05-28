package com.example.fangjingdong.view.presenter;

import com.example.fangjingdong.view.bean.DefaultAddrBean;

/**
 * Created by ASUS on 2018/3/7.
 */

public interface GetDefaultAddrPresenterInter {
    void onGetDefaultAddrSuccess(DefaultAddrBean defaultAddrBean);

    void onGetDefaultAddrEmpty();
}
