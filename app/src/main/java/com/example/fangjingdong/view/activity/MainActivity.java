package com.example.fangjingdong.view.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fangjingdong.R;
import com.example.fangjingdong.view.fragment.FragmentFaXian;
import com.example.fangjingdong.view.fragment.FragmentFenLei;
import com.example.fangjingdong.view.fragment.FragmentHome;
import com.example.fangjingdong.view.fragment.FragmentMy;
import com.example.fangjingdong.view.fragment.FragmentShopCart;
import com.example.fangjingdong.view.shujuku.AddrDao;
import com.example.fangjingdong.view.util.ChenJinUtil;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radio_group;
    private FragmentHome fragmentHome;
    private FragmentManager manager;
    private FragmentFenLei fragmentFenLei;
    private FragmentFaXian fragmentFaXian;
    private FragmentShopCart fragmentShopCart;
    private FragmentMy fragmentMy;
    private RadioButton radio_shouye;
    private RadioButton radio_fenlei;
    private RadioButton radio_faxian;
    private RadioButton radio_cart;
    private RadioButton radio_my;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        fragmentHome = new FragmentHome();
        radio_shouye = (RadioButton) findViewById(R.id.radio_shouye);
        radio_fenlei = (RadioButton) findViewById(R.id.radio_fenlei);
        radio_faxian = (RadioButton) findViewById(R.id.radio_faxian);
        radio_cart = (RadioButton) findViewById(R.id.radio_cart);
        radio_my = (RadioButton) findViewById(R.id.radio_my);
        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.frame,fragmentHome).commit();
        radio_group.setOnCheckedChangeListener(this);

        ChenJinUtil.startChenJin(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragmentHome!=null){
            transaction.hide(fragmentHome);
        }
        if (fragmentFenLei!=null){
            transaction.hide(fragmentFenLei);
        }
        if (fragmentFaXian!=null){
            transaction.hide(fragmentFaXian);
        }
        if (fragmentShopCart!=null){
            transaction.hide(fragmentShopCart);
        }
        if (fragmentMy!=null){
            transaction.hide(fragmentMy);
        }
        switch (i){
            case R.id.radio_shouye:
                if (fragmentHome==null){
                    fragmentHome = new FragmentHome();
                    transaction.add(R.id.frame,fragmentHome);
                }else {
                    transaction.show(fragmentHome);
                }
                break;
            case R.id.radio_fenlei:
                if (fragmentFenLei==null){
                    fragmentFenLei = new FragmentFenLei();
                    transaction.add(R.id.frame,fragmentFenLei);
                }else {
                    transaction.show(fragmentFenLei);
                }
                break;
            case R.id.radio_faxian:
                if (fragmentFaXian==null){
                    fragmentFaXian = new FragmentFaXian();
                    transaction.add(R.id.frame,fragmentFaXian);
                }else {
                    transaction.show(fragmentFaXian);
                }
                break;
            case R.id.radio_cart:
                if (fragmentShopCart==null){
                    fragmentShopCart = new FragmentShopCart();
                    transaction.add(R.id.frame,fragmentShopCart);
                }else {
                    transaction.show(fragmentShopCart);
                }
                break;
            case R.id.radio_my:
                if (fragmentMy==null){
                    fragmentMy = new FragmentMy();
                    transaction.add(R.id.frame,fragmentMy);
                }else {
                    transaction.show(fragmentMy);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }
}
