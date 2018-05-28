package com.example.fangjingdong.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fangjingdong.R;
import com.example.fangjingdong.view.bean.CartBean;


import java.util.ArrayList;

/**
 * Created by Dash on 2018/2/24.
 */
public class SureOrderAdapter extends RecyclerView.Adapter<SureOrderAdapter.ViewHolder>{
    private ArrayList<CartBean.DataBean.ListBean> list_selected;
    private Context context;

    public SureOrderAdapter(Context context, ArrayList<CartBean.DataBean.ListBean> list_selected) {
        this.context = context;
        this.list_selected = list_selected;
    }


    @Override
    public SureOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.sure_order_item_layout,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SureOrderAdapter.ViewHolder holder, int position) {
        CartBean.DataBean.ListBean listBean = list_selected.get(position);

        //赋值
        Glide.with(context).load(listBean.getImages().split("\\|")[0]).into(holder.sure_item_image);
        holder.sure_item_title.setText(listBean.getTitle());
        holder.sure_item_price.setText("¥"+listBean.getBargainPrice());
        holder.sure_item_num.setText("x"+listBean.getNum());
    }

    @Override
    public int getItemCount() {
        return list_selected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView sure_item_image;
        public TextView sure_item_title;
        public TextView sure_item_price;
        public TextView sure_item_num;
        public ViewHolder(View itemView) {
            super(itemView);
            sure_item_image = itemView.findViewById(R.id.sure_item_image);
            sure_item_title = itemView.findViewById(R.id.sure_item_title);
            sure_item_price = itemView.findViewById(R.id.sure_item_price);
            sure_item_num = itemView.findViewById(R.id.sure_item_num);
        }
    }
}
