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
import com.example.fangjingdong.view.bean.HengBean;

import java.util.List;

/**
 * Created by ASUS on 2018/1/24.
 */

public class HengAdapter extends RecyclerView.Adapter<HengAdapter.ViewHolder> {
    private List<HengBean.DataBean> heng;
    private Context context;
    private ClickListener clickListener;
    public HengAdapter(List<HengBean.DataBean> heng, Context context) {
        this.heng = heng;
        this.context = context;
    }

    @Override
    public HengAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.heng_layout,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HengAdapter.ViewHolder holder, final int position) {
        String[] split = heng.get(position).getIcon().split("\\|");
        Glide.with(context).load(split[0]).into(holder.heng_img);
        holder.heng_text.setText(heng.get(position).getName());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               clickListener.OnitemClicklistener(position);
           }
       });
    }

    @Override
    public int getItemCount() {
        return heng.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView heng_text;
        private ImageView heng_img;

        public ViewHolder(View itemView) {
            super(itemView);
            heng_img = itemView.findViewById(R.id.heng_img);
            heng_text = itemView.findViewById(R.id.heng_text);
        }
    }

    public void OnItemClick(ClickListener clickListener){
        this.clickListener=clickListener;
    }
}
