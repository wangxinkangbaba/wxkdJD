package com.example.fangjingdong.view.presenter;

import com.example.fangjingdong.view.IView.FaXianMain;
import com.example.fangjingdong.view.bean.FaXianBean;
import com.example.fangjingdong.view.model.FaXianModel;

/**
 * Created by ASUS on 2018/3/23.
 */

public class FaXianPresenter implements FaXianpre {

    private FaXianMain faXianMain;
    private FaXianModel faXianModel;

    public FaXianPresenter(FaXianMain faXianMain) {
        this.faXianMain=faXianMain;
        faXianModel = new FaXianModel(this);
    }

    public void getDate(String s, String uid) {
        faXianModel.getDateUrl(s,uid);
    }

    @Override
    public void OnSucess(FaXianBean faXianBean) {
        faXianMain.FaXianMainSuccess(faXianBean);
    }
}
