package com.example.fangjingdong.view.presenter;

import com.example.fangjingdong.view.IView.IBannerMain;
import com.example.fangjingdong.view.bean.BannerBean;
import com.example.fangjingdong.view.model.BannerModel;

/**
 * Created by ASUS on 2018/1/23.
 */

public class Bannerpre implements IBannerpre{

    private  BannerModel bannerModel;
    private IBannerMain iBannerMain;

    public Bannerpre(IBannerMain iBannerMain){
        this.iBannerMain=iBannerMain;
        bannerModel = new BannerModel(this);
    }

    public void getDate(String s) {
        bannerModel.getDateUrl(s);
    }

    @Override
    public void OnSuccess(BannerBean bannerBean) {
        iBannerMain.IBannerSucces(bannerBean);
    }
}
