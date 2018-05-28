package com.example.fangjingdong.view.presenter;


import com.example.fangjingdong.view.IView.UserInforInter;
import com.example.fangjingdong.view.activity.Gerenzhongxin;
import com.example.fangjingdong.view.bean.UserInfoBean;
import com.example.fangjingdong.view.model.UserInfoModel;

/**
 * Created by Dash on 2018/2/23.
 */
public class UserInfoPresenter implements UserInfoPresenterInter {

    private  UserInfoModel userInfoModel;
    private  UserInforInter userInforInter;

    public UserInfoPresenter(UserInforInter userInforInter) {
        this.userInforInter = userInforInter;
        userInfoModel = new UserInfoModel(this);
    }



    public void getUserInfo(String userInfoUrl, String uid) {

        userInfoModel.getUserInfo(userInfoUrl,uid);

    }

    @Override
    public void onUserInfoSUccess(UserInfoBean userInfoBean) {
        userInforInter.onUserInforSuccess(userInfoBean);
    }
}
