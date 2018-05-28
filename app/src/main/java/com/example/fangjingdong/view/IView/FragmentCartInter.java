package com.example.fangjingdong.view.IView;


import com.example.fangjingdong.view.bean.CartBean;

/**
 * Created by Dash on 2018/1/30.
 */
public interface FragmentCartInter {
    void getCartDataNull();

    void getCartDataSuccess(CartBean cartBean);
}
