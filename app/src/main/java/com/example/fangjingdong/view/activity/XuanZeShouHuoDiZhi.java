package com.example.fangjingdong.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.GetAllAddrInter;
import com.example.fangjingdong.view.adapter.GetAllAddrAdapter;
import com.example.fangjingdong.view.bean.GetAllAddrBean;
import com.example.fangjingdong.view.presenter.GetAllAddrPresenter;
import com.example.fangjingdong.view.util.CommonUtils;
import com.example.fangjingdong.view.util.JieKou;

public class XuanZeShouHuoDiZhi extends AppCompatActivity implements View.OnClickListener, GetAllAddrInter {
    private ImageView imageView;
    private TextView text_manage;
    private ListView list_view_addr;
    private GetAllAddrPresenter getAllAddrPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_di_zhi);
        imageView = (ImageView) findViewById(R.id.detail_image_back);
        text_manage = (TextView) findViewById(R.id.text_manage);
        list_view_addr = (ListView) findViewById(R.id.list_view_addr);

        imageView.setOnClickListener(this);
        text_manage.setOnClickListener(this);

        getAllAddrPresenter = new GetAllAddrPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllAddrPresenter.getAllAddr(JieKou.GET_ALL_ADDR_URL, CommonUtils.getString("uid"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.detail_image_back:
                finish();
                break;
            case R.id.text_manage://管理地址
                Intent intent = new Intent(XuanZeShouHuoDiZhi.this,GuanLiDiZhi.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onGetAllAddrSuccess(final GetAllAddrBean getAllAddrBean) {
        if ("0".equals(getAllAddrBean.getCode())) {

            //设置适配器
            GetAllAddrAdapter getAllAddrAdapter = new GetAllAddrAdapter(XuanZeShouHuoDiZhi.this, getAllAddrBean.getData());
            list_view_addr.setAdapter(getAllAddrAdapter);

            //设置条目的点击事件
            list_view_addr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Intent intent = new Intent();

                    intent.putExtra("addrBean",getAllAddrBean.getData().get(position));

                    setResult(2002,intent);
                    finish();

                }
            });

        }
    }
}
