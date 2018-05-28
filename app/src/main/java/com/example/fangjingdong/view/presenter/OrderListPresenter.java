package com.example.fangjingdong.view.presenter;

import com.example.fangjingdong.view.IView.FragmentOrderListInter;
import com.example.fangjingdong.view.bean.OrderListBean;
import com.example.fangjingdong.view.fragment.FragmentQuanBu;
import com.example.fangjingdong.view.model.OrderListModel;

/**
 * Created by ASUS on 2018/3/2.
 */

public class OrderListPresenter implements OrderListPresenterInter {
    private FragmentOrderListInter fragmentOrderListInter;
    private OrderListModel orderListModel;

    public OrderListPresenter(FragmentOrderListInter fragmentOrderListInter ) {
        this.fragmentOrderListInter=fragmentOrderListInter;
        orderListModel = new OrderListModel(this);
    }

    public void getOrderData(String orderListUrl, String uid, int page) {
        orderListModel.getOrderData(orderListUrl,uid,page);
    }

    @Override
    public void OnSuccess(OrderListBean orderListBean) {
            fragmentOrderListInter.FragmentOrderListInterSuccess(orderListBean);
    }
}
