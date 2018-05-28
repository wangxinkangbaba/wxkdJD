package com.example.fangjingdong.view.presenter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;


import com.example.fangjingdong.view.IView.GetProvinceInter;
import com.example.fangjingdong.view.bean.ProvinceBean;
import com.example.fangjingdong.view.model.GetProvinceModel;

import java.util.List;

/**
 * Created by Dash on 2018/2/27.
 */
public class GetProvincePresenter implements GetProvincePresenterInter {

    private GetProvinceInter getProvinceInter;
    private GetProvinceModel getProvinceModel;

    public GetProvincePresenter(GetProvinceInter getProvinceInter) {
        this.getProvinceInter = getProvinceInter;
        getProvinceModel = new GetProvinceModel(this);
    }

    public void getProvince(Context context) {
        getProvinceModel.getProvince(context);
    }

    @Override
    public void onGetProvince(List<ProvinceBean> list) {
        getProvinceInter.onGetProvince(list);
    }
}
