package com.example.fangjingdong.view.presenter;


import com.example.fangjingdong.view.bean.CartBean;

/**
 * Created by Dash on 2018/1/30.
 */
public interface CartPresenterInter {
    void getCartDataNull();

    void getCartDataSuccess(CartBean cartBean);
}
