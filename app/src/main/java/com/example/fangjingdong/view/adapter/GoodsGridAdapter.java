package com.example.fangjingdong.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.ClickListener;
import com.example.fangjingdong.view.bean.GoodsBean;


import java.util.List;

/**
 * Created by ASUS on 2018/1/6.
 */

public class GoodsGridAdapter extends RecyclerView.Adapter<GoodsGridAdapter.ViewHolder> {
    private List<GoodsBean.DataBean> list;
    private Context context;
    private ClickListener clicklistener;

    public GoodsGridAdapter(List<GoodsBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public GoodsGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.goods_gridview,null);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GoodsGridAdapter.ViewHolder holder, final int position) {
        holder.tv.setText(list.get(position).getTitle());
        holder.price.setText("￥"+list.get(position).getPrice());
        String[] split = list.get(position).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicklistener.OnitemClicklistener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private  TextView tv;
        private TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.grid_price);
            tv = itemView.findViewById(R.id.biao);
            img = itemView.findViewById(R.id.imgs);
        }
    }
    public void OnItemClick(ClickListener clicklistener){
        this.clicklistener=clicklistener;
    }
}
