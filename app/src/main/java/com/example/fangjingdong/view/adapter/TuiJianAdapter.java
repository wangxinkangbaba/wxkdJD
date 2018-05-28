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

public class TuiJianAdapter extends RecyclerView.Adapter<TuiJianAdapter.ViewHolder> {
    private BannerBean.TuijianBean tuijian;
    private Context context;
    private ClickListener clickListener;
    public TuiJianAdapter(BannerBean.TuijianBean tuijian, Context context) {
        this.tuijian = tuijian;
        this.context = context;
    }

    @Override
    public TuiJianAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.tuijian_layout,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TuiJianAdapter.ViewHolder holder, final int position) {
        String[] split = tuijian.getList().get(position).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.image_tuijian);
        holder.text_tuijian.setText(tuijian.getList().get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.OnitemClicklistener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tuijian.getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text_tuijian;
        private ImageView image_tuijian;

        public ViewHolder(View itemView) {
            super(itemView);
            image_tuijian = itemView.findViewById(R.id.image_tuijian);
            text_tuijian = itemView.findViewById(R.id.text_tuijian);
        }
    }
    public void OnItemClick(ClickListener clickListener){
        this.clickListener=clickListener;
    };
}
