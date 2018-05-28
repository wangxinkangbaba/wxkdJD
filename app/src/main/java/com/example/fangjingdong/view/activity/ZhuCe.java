package com.example.fangjingdong.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.IZhuMain;
import com.example.fangjingdong.view.bean.ZhuCeBean;
import com.example.fangjingdong.view.presenter.ZhuCePre;
import com.example.fangjingdong.view.util.JieKou;

public class ZhuCe extends AppCompatActivity implements IZhuMain{

    private EditText zhuce_phone;
    private EditText zhuce_psw;
    private ZhuCePre zhuCePre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        zhuce_phone = (EditText) findViewById(R.id.zhuce_phone);
        zhuce_psw = (EditText) findViewById(R.id.zhuce_psw);
        zhuCePre = new ZhuCePre(this);
    }

    public void zhuce(View view){
        String phone = zhuce_phone.getText().toString();
        String psw = zhuce_psw.getText().toString();
        zhuCePre.getDate(JieKou.ZHUCE_URL,phone,psw);
    }

    @Override
    public void IZhuSuccess(final ZhuCeBean zhuCeBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (zhuCeBean.getCode().equals("0")){
                    Intent intent = new Intent(ZhuCe.this, Login.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(ZhuCe.this, zhuCeBean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void fan(View view){
        finish();
    }
}
