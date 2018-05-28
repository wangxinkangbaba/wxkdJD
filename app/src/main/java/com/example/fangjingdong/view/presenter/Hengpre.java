package com.example.fangjingdong.view.presenter;

import com.example.fangjingdong.view.IView.IHengMain;
import com.example.fangjingdong.view.bean.HengBean;
import com.example.fangjingdong.view.model.HengModel;

/**
 * Created by ASUS on 2018/1/24.
 */

public class Hengpre implements IHengpre{

    private  HengModel hengModel;
    private IHengMain iHengMain;

    public Hengpre(IHengMain iHengMain){
        this.iHengMain=iHengMain;
        hengModel = new HengModel(this);
    }

    public void getDate(String s) {
        hengModel.getDateUrl(s);
    }

    @Override
    public void OnSuccess(HengBean hengBean) {
        iHengMain.IHengSuccess(hengBean);
    }
}
