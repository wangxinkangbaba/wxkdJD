package com.example.fangjingdong.view.IView;

import com.example.fangjingdong.view.bean.LoginBean;

/**
 * Created by ASUS on 2018/1/31.
 */

public interface ILoginMain {
    void ILoginSuccess(LoginBean loginBean);

    void getLoginSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl);

}
