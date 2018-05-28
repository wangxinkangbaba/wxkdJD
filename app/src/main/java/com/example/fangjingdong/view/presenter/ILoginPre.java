package com.example.fangjingdong.view.presenter;

import com.example.fangjingdong.view.bean.LoginBean;

/**
 * Created by ASUS on 2018/1/31.
 */

public interface ILoginPre {
    void OnSuccess(LoginBean loginBean);

    void onSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl);
}
