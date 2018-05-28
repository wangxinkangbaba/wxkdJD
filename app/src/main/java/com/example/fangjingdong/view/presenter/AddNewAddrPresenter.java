package com.example.fangjingdong.view.presenter;


import com.example.fangjingdong.view.IView.AddNewAddrInter;
import com.example.fangjingdong.view.bean.AddNewAddrBean;
import com.example.fangjingdong.view.model.AddNewAddrModel;

/**
 * Created by Dash on 2018/2/26.
 */
public class AddNewAddrPresenter implements AddNewAddrPresenterInter {

    private AddNewAddrInter addNewAddrInter;
    private AddNewAddrModel addNewAddrModel;

    public AddNewAddrPresenter(AddNewAddrInter addNewAddrInter) {
        this.addNewAddrInter = addNewAddrInter;
        addNewAddrModel = new AddNewAddrModel(this);
    }

    public void addNewAddr(String addNewAddrUrl, String uid, String addr, String phone, String name) {

        addNewAddrModel.addNewAddr(addNewAddrUrl,uid,addr,phone,name);
    }

    @Override
    public void onAddAddrSuccess(AddNewAddrBean addNewAddrBean) {
        addNewAddrInter.onAddNewAddrSuccess(addNewAddrBean);
    }
}
