package com.example.fangjingdong.view.presenter;

import com.example.fangjingdong.view.IView.CreateOrderInter;
import com.example.fangjingdong.view.bean.CreateOrderBean;
import com.example.fangjingdong.view.model.CreatDingDanModel;

/**
 * Created by ASUS on 2018/3/2.
 */

public class CreatDingDanPre implements CreateOrderPresenterInter{

    private CreateOrderInter createOrderInter;
    private CreatDingDanModel creatDingDanModel;

    public CreatDingDanPre(CreateOrderInter createOrderInter){
        this.createOrderInter=createOrderInter;
        creatDingDanModel = new CreatDingDanModel(this);
    }

    public void getDate(String createOrderUrl, String uid, double price) {
        creatDingDanModel.getDateUrl(createOrderUrl,uid,price);
    }

    @Override
    public void OnSuccess(CreateOrderBean createOrderBean) {
        createOrderInter.CreatOrderSuccess(createOrderBean);
    }
}
