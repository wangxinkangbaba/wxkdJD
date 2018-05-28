package com.example.fangjingdong.view.presenter;

import com.example.fangjingdong.view.IView.IZhuMain;
import com.example.fangjingdong.view.bean.ZhuCeBean;
import com.example.fangjingdong.view.model.ZhuCeModel;

/**
 * Created by ASUS on 2018/2/2.
 */

public class ZhuCePre implements IZhuPre{

    private IZhuMain iZhuMain;
    private  ZhuCeModel zhuCeModel;

    public ZhuCePre(IZhuMain iZhuMain){
        this.iZhuMain=iZhuMain;
        zhuCeModel = new ZhuCeModel(this);
    }

    public void getDate(String zhuceUrl, String phone, String psw) {
        zhuCeModel.getDateUrl(zhuceUrl,phone,psw);
    }

    @Override
    public void OnSuccess(ZhuCeBean zhuCeBean) {
        iZhuMain.IZhuSuccess(zhuCeBean);
    }
}
