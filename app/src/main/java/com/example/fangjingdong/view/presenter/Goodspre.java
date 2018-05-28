package com.example.fangjingdong.view.presenter;

import com.example.fangjingdong.view.IView.IGoodsMain;
import com.example.fangjingdong.view.bean.GoodsBean;
import com.example.fangjingdong.view.model.GoodsModel;

/**
 * Created by ASUS on 2018/1/26.
 */

public class Goodspre implements IGoodspre{

    private IGoodsMain iGoodsMain;
    private  GoodsModel goodsModel;

    public Goodspre(IGoodsMain iGoodsMain){
        this.iGoodsMain=iGoodsMain;
        goodsModel = new GoodsModel(this);
    }

    public void getDate(String keywords,int page) {
        goodsModel.getDateUrl(keywords,page);
    }

    @Override
    public void OnSuccess(GoodsBean goodsBean) {
        iGoodsMain.IGoodsSuccess(goodsBean);
    }
}
