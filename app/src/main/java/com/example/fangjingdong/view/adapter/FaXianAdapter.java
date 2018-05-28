package com.example.fangjingdong.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fangjingdong.R;
import com.example.fangjingdong.view.bean.FaXianBean;

import java.util.List;

/**
 * Created by ASUS on 2018/3/23.
 */

public class FaXianAdapter extends RecyclerView.Adapter<FaXianAdapter.ViewHolder> {
    private List<FaXianBean.DataBean> data;
    private Context context;

    public FaXianAdapter(List<FaXianBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public FaXianAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.faxian,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FaXianAdapter.ViewHolder holder, int position) {
        holder.faxian_text.setText(data.get(position).getWorkDesc());
        String cover = data.get(position).getCover();
        Glide.with(context).load(cover).into(holder.faxian_img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView faxian_text;
        private ImageView faxian_img;

        public ViewHolder(View itemView) {
            super(itemView);
            faxian_img = itemView.findViewById(R.id.faxian_img);
            faxian_text = itemView.findViewById(R.id.faxian_text);
        }
    }
}
