package com.example.fangjingdong.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.IGoodsMain;
import com.example.fangjingdong.view.IView.ClickListener;
import com.example.fangjingdong.view.adapter.GoodsGridAdapter;
import com.example.fangjingdong.view.adapter.GoodsListAdapter;
import com.example.fangjingdong.view.bean.GoodsBean;
import com.example.fangjingdong.view.presenter.Goodspre;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class Goods extends AppCompatActivity implements IGoodsMain{

    private TextView ed_sou;
    private CheckBox qiehuan;
    private RecyclerView goods_rev;
    private Goodspre goodspre;
    private int page=1;
    private boolean flag=false;
    private SmartRefreshLayout goods_srf;
    private String keywords;
    private List<GoodsBean.DataBean> listall=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ed_sou = (TextView) findViewById(R.id.ed_sou);
        qiehuan = (CheckBox) findViewById(R.id.qiehuan);
        goods_rev = (RecyclerView) findViewById(R.id.goods_rev);
        goods_srf = (SmartRefreshLayout) findViewById(R.id.goods_srf);
        Intent intent = getIntent();
        keywords = intent.getStringExtra("keywords");
        goods_rev.setLayoutManager(new LinearLayoutManager(Goods.this));
        goods_srf.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                listall.clear();
                goodspre.getDate(keywords,page);
                goods_srf.finishRefresh(2000);
            }
        });
        goods_srf.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                goodspre.getDate(keywords,page);

                goods_srf.finishLoadmore(2000);
            }
        });
        goodspre = new Goodspre(this);
        goodspre.getDate(keywords,page);
    }
    public void fan(View view){
        finish();
    }

    @Override
    public void IGoodsSuccess(final GoodsBean goodsBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listall.addAll(goodsBean.getData());
                final List<GoodsBean.DataBean> data = goodsBean.getData();
                GoodsListAdapter goodsListAdapter = new GoodsListAdapter(data, Goods.this);
                goods_rev.setAdapter(goodsListAdapter);
                goodsListAdapter.OnItemClick(new ClickListener() {
                    @Override
                    public void OnitemClicklistener(int position) {
                        Intent intent = new Intent(Goods.this, Xiangqing.class);
                        int pid = data.get(position).getPid();
                        intent.putExtra("pid",pid+"");
                        startActivity(intent);
                    }
                });
                qiehuan.setChecked(flag);
                qiehuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (flag){
                            goods_rev.setLayoutManager(new LinearLayoutManager(Goods.this, OrientationHelper.VERTICAL,true));
                            GoodsListAdapter goodsListAdapter = new GoodsListAdapter(data, Goods.this);
                            goods_rev.setAdapter(goodsListAdapter);
                            goodsListAdapter.OnItemClick(new ClickListener() {
                                @Override
                                public void OnitemClicklistener(int position) {
                                    Intent intent = new Intent(Goods.this, Xiangqing.class);
                                    int pid = data.get(position).getPid();
                                    intent.putExtra("pid",pid+"");
                                    startActivity(intent);
                                }
                            });
                            qiehuan.setChecked(false);
                            flag=qiehuan.isChecked();
                        }else {
                            goods_rev.setLayoutManager(new GridLayoutManager(Goods.this,2, OrientationHelper.VERTICAL,true));
                            final GoodsGridAdapter goodsGridAdapter = new GoodsGridAdapter(data, Goods.this);
                            goods_rev.setAdapter(goodsGridAdapter);
                            goods_srf.setOnRefreshListener(new OnRefreshListener() {
                                @Override
                                public void onRefresh(RefreshLayout refreshlayout) {
                                    page=1;
                                    listall.clear();
                                    goods_rev.setLayoutManager(new GridLayoutManager(Goods.this,2, OrientationHelper.VERTICAL,true));
                                    goods_rev.setAdapter(goodsGridAdapter);
                                    goods_srf.finishRefresh(2000);
                                }
                            });
                            goods_srf.setOnLoadmoreListener(new OnLoadmoreListener() {
                                @Override
                                public void onLoadmore(RefreshLayout refreshlayout) {
                                    page++;
                                    goods_rev.setLayoutManager(new GridLayoutManager(Goods.this,2, OrientationHelper.VERTICAL,true));
                                    goods_rev.setAdapter(goodsGridAdapter);
                                    goods_srf.finishLoadmore(2000);
                                }
                            });
                            goodsGridAdapter.OnItemClick(new ClickListener() {
                                @Override
                                public void OnitemClicklistener(int position) {
                                    Intent intent = new Intent(Goods.this, Xiangqing.class);
                                    int pid = data.get(position).getPid();
                                    intent.putExtra("pid",pid+"");
                                    startActivity(intent);
                                }
                            });
                            qiehuan.setChecked(true);
                            flag=qiehuan.isChecked();
                        }
                    }
                });
            }
        });
    }

}
