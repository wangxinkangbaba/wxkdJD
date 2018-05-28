package com.example.fangjingdong.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fangjingdong.R;
import com.example.fangjingdong.view.fragment.FragmentProvince;

public class XuanZeDiZhi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuan_ze_di_zhi);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new FragmentProvince()).commit();
    }
}
