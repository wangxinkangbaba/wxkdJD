package com.example.fangjingdong.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.FaXianMain;
import com.example.fangjingdong.view.adapter.FaXianAdapter;
import com.example.fangjingdong.view.bean.FaXianBean;
import com.example.fangjingdong.view.presenter.FaXianPresenter;
import com.example.fangjingdong.view.util.ChenJinUtil;
import com.example.fangjingdong.view.util.CommonUtils;

import java.util.List;

/**
 * Created by ASUS on 2018/1/23.
 */

public class FragmentFaXian extends Fragment{

    private RecyclerView faxian_rev;
    private FaXianPresenter faXianPresenter;
    private WebView web;
    private String url="http://kan.sogou.com/live/";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_faxian,container,false);
//        faxian_rev = (RecyclerView) view.findViewById(R.id.faxian_rev);
//        faXianPresenter = new FaXianPresenter(this);
        web = (WebView) view.findViewById(R.id.web);
        web.loadUrl(url);
        web.setWebViewClient(new WebViewClient());//在当前应用打开,而不是去浏览器
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initChenJin();
//        faXianPresenter.getDate("https://www.zhaoapi.cn/quarter/getVideos", CommonUtils.getString("uid"));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (! hidden) {
            initChenJin();
        }
    }

    private void initChenJin() {
        ChenJinUtil.setStatusBarColor(getActivity(), getResources().getColor(R.color.colorPrimaryDark));
    }

//    @Override
//    public void FaXianMainSuccess(FaXianBean faXianBean) {
//        if ("0".equals(faXianBean.getCode())){
//            List<FaXianBean.DataBean> data = faXianBean.getData();
//            faxian_rev.setLayoutManager(new LinearLayoutManager(getActivity()));
//            FaXianAdapter faXianAdapter = new FaXianAdapter(data, getActivity());
//            faxian_rev.setAdapter(faXianAdapter);
//
//        }else {
//            Toast.makeText(getActivity(), "获取列表失败", Toast.LENGTH_SHORT).show();
//        }
//    }
}
