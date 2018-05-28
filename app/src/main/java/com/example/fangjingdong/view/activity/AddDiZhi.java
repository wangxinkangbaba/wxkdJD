package com.example.fangjingdong.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.AddNewAddrInter;
import com.example.fangjingdong.view.application.DashApplication;
import com.example.fangjingdong.view.bean.AddNewAddrBean;
import com.example.fangjingdong.view.presenter.AddNewAddrPresenter;
import com.example.fangjingdong.view.util.CommonUtils;
import com.example.fangjingdong.view.util.JieKou;

import java.util.List;

public class AddDiZhi extends AppCompatActivity implements View.OnClickListener, AddNewAddrInter {
    private ImageView detail_image_back;
    private TextView text_save;
    private EditText edit_person;
    private EditText edit_phone;
    private TextView edit_area;
    private EditText edit_road;
    private AddNewAddrPresenter addNewAddrPresenter;
    private LinearLayout linear_area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_di_zhi);
        detail_image_back = (ImageView) findViewById(R.id.detail_image_back);
        text_save = (TextView) findViewById(R.id.text_save);
        edit_person = (EditText) findViewById(R.id.edit_person);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_area = (TextView) findViewById(R.id.edit_area);
        edit_road = (EditText) findViewById(R.id.edit_road);
        linear_area = (LinearLayout) findViewById(R.id.linear_area);
        detail_image_back.setOnClickListener(this);
        text_save.setOnClickListener(this);
        linear_area.setOnClickListener(this);//选择地址
        addNewAddrPresenter = new AddNewAddrPresenter(this);
        //初始化并且拿到数据库的对象


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.detail_image_back:

                finish();//没传值回去...此时确认订单需要finish

                break;
            case R.id.text_save://保存
                //请求添加新地址的接口...点击保存的时候需要做一系列的非空判断
                //uid=71&addr=北京市昌平区金域国际1-1-1&mobile=18612991023&name=kson
                addNewAddrPresenter.addNewAddr(JieKou.ADD_NEW_ADDR_URL, CommonUtils.getString("uid"),edit_area.getText().toString()+edit_road.getText().toString(),edit_phone.getText().toString(),edit_person.getText().toString());


                break;
            case R.id.linear_area://选择地址
                Intent intent = new Intent(AddDiZhi.this,XuanZeDiZhi.class);
                startActivityForResult(intent,3001);
                break;
        }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==3001 && resultCode == 3002) {
            if (data == null) {
                return;
            }

            String addr = data.getStringExtra("addr");
            edit_area.setText(addr);

        }
    }

    @Override
    public void onAddNewAddrSuccess(AddNewAddrBean addNewAddrBean) {
        if ("0".equals(addNewAddrBean.getCode())) {//保存成功
            //请求添加成功之后...回传值给确认订单页面进行显示

            Intent intent = new Intent();

            intent.putExtra("name",edit_person.getText().toString());
            intent.putExtra("phone",edit_phone.getText().toString());
            intent.putExtra("addr",edit_area.getText().toString()+edit_road.getText().toString());

            setResult(1002,intent);

            finish();

        }
    }
}
