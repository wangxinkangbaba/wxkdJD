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
import com.example.fangjingdong.view.adapter.ManageAddrAdapter;
import com.example.fangjingdong.view.bean.GetAllAddrBean;
import com.example.fangjingdong.view.presenter.GetAllAddrPresenter;
import com.example.fangjingdong.view.util.CommonUtils;
import com.example.fangjingdong.view.util.JieKou;

public class GuanLiDiZhi extends AppCompatActivity implements GetAllAddrInter, View.OnClickListener {
    private GetAllAddrPresenter getAllAddrPresenter;
    private ImageView detail_image_back;
    private ListView list_view_addr;
    private TextView text_add_new;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guan_li_di_zhi);
        detail_image_back = (ImageView) findViewById(R.id.detail_image_back);
        list_view_addr = (ListView) findViewById(R.id.list_view_addr);
        text_add_new = (TextView) findViewById(R.id.text_add_new);

        getAllAddrPresenter = new GetAllAddrPresenter(this);

        text_add_new.setOnClickListener(this);
        detail_image_back.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllAddrPresenter.getAllAddr(JieKou.GET_ALL_ADDR_URL, CommonUtils.getString("uid"));
    }

    @Override
    public void onGetAllAddrSuccess(GetAllAddrBean getAllAddrBean) {
        if ("0".equals(getAllAddrBean.getCode())) {

            //设置适配器
            ManageAddrAdapter manageAddrAdapter = new ManageAddrAdapter(GuanLiDiZhi.this, getAllAddrBean.getData(), getAllAddrPresenter);
            list_view_addr.setAdapter(manageAddrAdapter);


            //设置条目的点击事件
            list_view_addr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                }
            });
        }
    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.detail_image_back:
                    finish();;
                    break;
                case R.id.text_add_new:
                    Intent intent = new Intent(GuanLiDiZhi.this,AddDiZhi.class);
                    startActivity(intent);
                    break;
            }
    }
}
