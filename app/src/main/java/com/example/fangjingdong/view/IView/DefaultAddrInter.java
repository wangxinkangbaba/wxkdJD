package com.example.fangjingdong.view.IView;

import com.example.fangjingdong.view.bean.DefaultAddrBean;

/**
 * Created by ASUS on 2018/3/7.
 */

public interface DefaultAddrInter {
    void onGetDefaultAddrSuccess(DefaultAddrBean defaultAddrBean);

    void onGetDefaultAddrEmpty();
}
