package com.example.fangjingdong.view.presenter;


import com.example.fangjingdong.view.IView.IFenMain;
import com.example.fangjingdong.view.bean.ZiFenBean;
import com.example.fangjingdong.view.model.FenModel;

/**
 * Created by ASUS on 2018/1/10.
 */

public class FenPre implements IFenPre{

    private IFenMain iFenMain;
    private FenModel fenModel;

    public FenPre(IFenMain iFenMain){
        this.iFenMain=iFenMain;
        fenModel = new FenModel(this);
    }

    public void getDate(String s,String cid) {
        fenModel.getDateUrl(s,cid);
    }

    @Override
    public void OnSuccess(ZiFenBean zifenBean) {
        iFenMain.FenSuccess(zifenBean);
    }
}
