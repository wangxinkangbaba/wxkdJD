package com.example.fangjingdong.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.FragmentOrderListInter;
import com.example.fangjingdong.view.adapter.OrderListAdapter;
import com.example.fangjingdong.view.bean.OrderListBean;
import com.example.fangjingdong.view.presenter.OrderListPresenter;
import com.example.fangjingdong.view.util.CommonUtils;
import com.example.fangjingdong.view.util.JieKou;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/3/2.
 */

public class FragmentQuanBu extends Fragment implements FragmentOrderListInter {
    private RecyclerView recyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private OrderListPresenter orderListPresenter;
    //分页
    private int page = 1;
    //大集合
    private List<OrderListBean.DataBean> listAll = new ArrayList<>();
    private OrderListAdapter orderListAdapter;
    private RelativeLayout relative_empty_order;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order_all_layout,container,false);
        recyclerView = view.findViewById(R.id.recycler_order);
        smartRefreshLayout = view.findViewById(R.id.smart_refresh);
        relative_empty_order = view.findViewById(R.id.relative_empty_order);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //获取订单列表的数据
        orderListPresenter = new OrderListPresenter(this);
        orderListPresenter.getOrderData(JieKou.ORDER_LIST_URL, CommonUtils.getString("uid"),page);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smartRefreshLayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page ++;
                orderListPresenter.getOrderData(JieKou.ORDER_LIST_URL, CommonUtils.getString("uid"),page);
            }
        });
    }

    @Override
    public void FragmentOrderListInterSuccess(OrderListBean orderListBean) {
        if ("0".equals(orderListBean.getCode())) {
            //添加到大集合
            listAll.addAll(orderListBean.getData());

            if (listAll.size() == 0) {
                relative_empty_order.setVisibility(View.VISIBLE);
                smartRefreshLayout.setVisibility(View.GONE);
            }else {
                relative_empty_order.setVisibility(View.GONE);
                smartRefreshLayout.setVisibility(View.VISIBLE);
            }

            //设置适配器
            setAdapter();

        }
    }

    private void setAdapter() {
        if (orderListAdapter == null) {
            orderListAdapter = new OrderListAdapter(getActivity(), listAll);
            recyclerView.setAdapter(orderListAdapter);
        }else {
            orderListAdapter.notifyDataSetChanged();
        }

        smartRefreshLayout.finishLoadmore();

    }

}
