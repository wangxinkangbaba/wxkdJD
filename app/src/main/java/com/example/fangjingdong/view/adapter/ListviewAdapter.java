package com.example.fangjingdong.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fangjingdong.R;
import com.example.fangjingdong.view.bean.HengBean;

import java.util.List;

/**
 * Created by ASUS on 2018/1/25.
 */

public class ListviewAdapter extends BaseAdapter {
    private List<HengBean.DataBean> data;
    private Context context;
    private int curPositon;
    public void setCurPositon(int curPositon) {

        this.curPositon = curPositon;

    }
    public int getCurPositon() {

        return curPositon;

    }
    public ListviewAdapter(List<HengBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=View.inflate(context, R.layout.listview_adapter,null);
        TextView lv_text = view.findViewById(R.id.lv_text);
        lv_text.setText(data.get(i).getName());
        if (i == curPositon) {

            view.setBackgroundColor(Color.TRANSPARENT);

            lv_text.setTextColor(Color.RED);

        }else{

            lv_text.setTextColor(Color.BLACK);

            view.setBackgroundColor(Color.WHITE);

        }
        return view;
    }
}
