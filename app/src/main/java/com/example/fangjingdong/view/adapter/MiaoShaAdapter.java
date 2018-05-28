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
import com.example.fangjingdong.view.bean.BannerBean;

/**
 * Created by ASUS on 2018/1/24.
 */

public class MiaoShaAdapter extends RecyclerView.Adapter<MiaoShaAdapter.ViewHolder> {
    private BannerBean.MiaoshaBean miaosha;
    private Context context;
    private ClickListener clickListener;
    public MiaoShaAdapter(BannerBean.MiaoshaBean miaosha, Context context) {
        this.miaosha = miaosha;
        this.context = context;
    }

    @Override
    public MiaoShaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.miaosha_layout,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MiaoShaAdapter.ViewHolder holder, final int position) {
        String[] split = miaosha.getList().get(position).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.heng_img);
        holder.heng_text.setText("¥"+miaosha.getList().get(position).getBargainPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.OnitemClicklistener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return miaosha.getList().size();
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
