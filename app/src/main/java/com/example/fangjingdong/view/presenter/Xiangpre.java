package com.example.fangjingdong.view.presenter;


import com.example.fangjingdong.view.IView.IXiangMain;
import com.example.fangjingdong.view.bean.XiangBean;
import com.example.fangjingdong.view.model.XiangModel;

/**
 * Created by ASUS on 2018/1/9.
 */

public class Xiangpre implements IXiangpre{

    private IXiangMain iXiangMain;
    private XiangModel xiangModel;

    public Xiangpre(IXiangMain iXiangMain){
        this.iXiangMain=iXiangMain;
        xiangModel = new XiangModel(this);
    }

    public void getDate(String pid) {
        xiangModel.getDateUrl(pid);
    }

    @Override
    public void OnSuccess(XiangBean xiangBean) {
        iXiangMain.XiangSuccess(xiangBean);
    }
}
