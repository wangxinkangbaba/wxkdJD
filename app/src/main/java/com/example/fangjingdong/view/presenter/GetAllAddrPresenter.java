package com.example.fangjingdong.view.presenter;


import com.example.fangjingdong.view.IView.GetAllAddrInter;
import com.example.fangjingdong.view.bean.GetAllAddrBean;
import com.example.fangjingdong.view.model.GetAllAddrModel;

/**
 * Created by Dash on 2018/2/27.
 */
public class GetAllAddrPresenter implements GetAllAddrPresenterInter {

    private GetAllAddrInter getAllAddrInter;
    private GetAllAddrModel getAllAddrModel;

    public GetAllAddrPresenter(GetAllAddrInter getAllAddrInter) {
        this.getAllAddrInter = getAllAddrInter;
        getAllAddrModel = new GetAllAddrModel(this);
    }

    public void getAllAddr(String getAllAddrUrl, String uid) {
        getAllAddrModel.getAllAddr(getAllAddrUrl,uid);
    }

    @Override
    public void onGetAllAddrSuccess(GetAllAddrBean getAllAddrBean) {
        getAllAddrInter.onGetAllAddrSuccess(getAllAddrBean);
    }
}
