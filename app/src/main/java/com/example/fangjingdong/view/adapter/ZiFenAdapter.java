package com.example.fangjingdong.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.ClickListener;
import com.example.fangjingdong.view.activity.Goods;
import com.example.fangjingdong.view.bean.ZiFenBean;

import java.util.List;

/**
 * Created by ASUS on 2018/1/10.
 */

public class ZiFenAdapter extends RecyclerView.Adapter<ZiFenAdapter.ViewHolder> {
    List<ZiFenBean.DataBean> list;
    private Context context;

    public ZiFenAdapter(List<ZiFenBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ZiFenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.zifenlei,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ZiFenAdapter.ViewHolder holder, int position) {
        holder.zifen_tv.setText(list.get(position).getName());
        final List<ZiFenBean.DataBean.ListBean> data = this.list.get(position).getList();
        if (data!=null){
            ZifenShujuAdapter zifenShujuAdapter = new ZifenShujuAdapter(data, context);
            holder.zifen_rev.setAdapter(zifenShujuAdapter);
            holder.zifen_rev.setLayoutManager(new GridLayoutManager(context,3));
            zifenShujuAdapter.OnItemClick(new ClickListener() {
                @Override
                public void OnitemClicklistener(int position) {
                    Intent intent = new Intent(context, Goods.class);
                    String keywords = data.get(position).getName();
                    intent.putExtra("keywords",keywords);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView zifen_tv;
        private RecyclerView zifen_rev;
        public ViewHolder(View itemView) {
            super(itemView);
             zifen_tv = itemView.findViewById(R.id.zifen_tv);
             zifen_rev = itemView.findViewById(R.id.zifen_rev);
        }
    }
}
