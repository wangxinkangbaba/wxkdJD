package com.example.fangjingdong.view.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.fangjingdong.R;
import com.example.fangjingdong.view.bean.ProvinceBean;

import java.util.List;

/**
 * Created by Dash on 2018/2/27.
 */
public class ProvinceAdapter extends BaseAdapter{
    private List<ProvinceBean> list;
    private Context context;

    public ProvinceAdapter(Context context, List<ProvinceBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.province_item_layout,null);
            holder = new ViewHolder();

            holder.textView = view.findViewById(R.id.text_name);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.textView.setText(list.get(i).getName());

        return view;
    }

    private class ViewHolder{
        TextView textView;
    }
}
