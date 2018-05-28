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
import com.example.fangjingdong.view.bean.ZiFenBean;


import java.util.List;

/**
 * Created by ASUS on 2018/1/10.
 */

public class ZifenShujuAdapter extends RecyclerView.Adapter<ZifenShujuAdapter.ViewHolder> {
    List<ZiFenBean.DataBean.ListBean> data;
    private Context context;
    private ClickListener clicklistener;
    public ZifenShujuAdapter(List<ZiFenBean.DataBean.ListBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ZifenShujuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.zifenshuju,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ZifenShujuAdapter.ViewHolder holder, final int position) {
        String[] icon = data.get(position).getIcon().split("\\|");
        Glide.with(context).load(icon[0]).into(holder.img);
        holder.name.setText(data.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicklistener.OnitemClicklistener(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  ImageView img;
        private  TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.zifen_img);
            name = itemView.findViewById(R.id.zifen_name);
        }
    }
    public void OnItemClick(ClickListener clicklistener){
        this.clicklistener=clicklistener;
    }
}
