package com.example.fangjingdong.view.presenter;

import com.example.fangjingdong.view.IView.ILoginMain;
import com.example.fangjingdong.view.bean.LoginBean;
import com.example.fangjingdong.view.model.LoginModel;

/**
 * Created by ASUS on 2018/1/31.
 */

public class Loginpre implements ILoginPre{

    private ILoginMain iLoginMain;
    private LoginModel loginModel;

    public Loginpre(ILoginMain iLoginMain){
        this.iLoginMain=iLoginMain;
        loginModel = new LoginModel(this);
    }

    public void getDate(String loginUrl, String phone, String psw) {
        loginModel.getDateUrl(loginUrl,phone,psw);
    }

    @Override
    public void OnSuccess(LoginBean loginBean) {
        iLoginMain.ILoginSuccess(loginBean);
    }

    @Override
    public void onSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl) {
        iLoginMain.getLoginSuccessByQQ(loginBean,ni_cheng,iconurl);
    }

    public void getLoginByQQ(String phone, String pwd, String ni_cheng, String iconurl) {
        loginModel.getLoginByQQ(phone,pwd,ni_cheng,iconurl);
    }
}
