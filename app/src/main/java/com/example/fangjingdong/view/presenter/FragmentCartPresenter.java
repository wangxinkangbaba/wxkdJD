package com.example.fangjingdong.view.presenter;


import com.example.fangjingdong.view.IView.FragmentCartInter;
import com.example.fangjingdong.view.bean.CartBean;
import com.example.fangjingdong.view.model.CartModel;

/**
 * Created by Dash on 2018/1/30.
 */
public class FragmentCartPresenter implements CartPresenterInter {

    private FragmentCartInter fragmentCartInter;
    private CartModel cartModel;

    public FragmentCartPresenter(FragmentCartInter fragmentCartInter) {
        this.fragmentCartInter = fragmentCartInter;

        cartModel = new CartModel(this);
    }

    public void getCartData(String selectCart, String uid) {

        cartModel.getCartData(selectCart,uid);

    }

    @Override
    public void getCartDataNull() {
        fragmentCartInter.getCartDataNull();
    }

    @Override
    public void getCartDataSuccess(CartBean cartBean) {
        fragmentCartInter.getCartDataSuccess(cartBean);
    }
}
