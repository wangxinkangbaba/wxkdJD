package com.example.fangjingdong.view.presenter;

import com.example.fangjingdong.view.IView.IAddCartMain;
import com.example.fangjingdong.view.bean.AddCartBean;
import com.example.fangjingdong.view.model.AddCartModel;

/**
 * Created by ASUS on 2018/2/1.
 */

public class AddCart implements IAddCartPre{

    private IAddCartMain iAddCartMain;
    private  AddCartModel addCartModel;

    public AddCart(IAddCartMain iAddCartMain){
        this.iAddCartMain=iAddCartMain;
        addCartModel = new AddCartModel(this);
    }

    public void getDate(String addCart, String uid, String pid) {
        addCartModel.getDateUrl(addCart,uid,pid);
    }

    @Override
    public void OnSuccess(AddCartBean addCartBean) {
        iAddCartMain.IAddCatSuccess(addCartBean);
    }
}
