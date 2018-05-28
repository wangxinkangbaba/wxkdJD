package com.example.fangjingdong.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


import com.example.fangjingdong.R;
import com.example.fangjingdong.view.bean.GetAllAddrBean;
import com.example.fangjingdong.view.presenter.GetAllAddrPresenter;
import com.example.fangjingdong.view.util.CommonUtils;
import com.example.fangjingdong.view.util.JieKou;
import com.example.fangjingdong.view.util.OkHttp3Util_03;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Dash on 2018/2/27.
 */
public class ManageAddrAdapter extends BaseAdapter{
    private GetAllAddrPresenter getAllAddrPresenter;
    private List<GetAllAddrBean.DataBean> data;
    private Context context;

    public ManageAddrAdapter(Context context, List<GetAllAddrBean.DataBean> data, GetAllAddrPresenter getAllAddrPresenter) {
        this.context = context;
        this.data = data;
        this.getAllAddrPresenter = getAllAddrPresenter;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.manage_addr_item_layout,null);
            holder = new ViewHolder();

            holder.text_name = view.findViewById(R.id.text_name);
            holder.text_phone = view.findViewById(R.id.text_phone);
            holder.text_addr = view.findViewById(R.id.text_addr);
            holder.checkBox = view.findViewById(R.id.check_box_default);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        final GetAllAddrBean.DataBean dataBean = data.get(position);
        holder.text_name.setText(dataBean.getName());
        holder.text_phone.setText(String.valueOf(dataBean.getMobile()));
        holder.text_addr.setText(dataBean.getAddr());

        //根据status选中checkBox
        if (dataBean.getStatus() == 1) {
            holder.checkBox.setChecked(true);
        }else {
            holder.checkBox.setChecked(false);
        }

        //点击事件...设置默认地址uid=71&addrid=3&status=1
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap<>();
                params.put("uid", CommonUtils.getString("uid"));
                params.put("addrid", String.valueOf(dataBean.getAddrid()));
                params.put("status", String.valueOf(holder.checkBox.isChecked()? 1:0));

                OkHttp3Util_03.doPost(JieKou.SET_DEFAULT_ADDR_URL, params, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            //重新请求新的数据进行展示
                            getAllAddrPresenter.getAllAddr(JieKou.GET_ALL_ADDR_URL, CommonUtils.getString("uid"));
                        }
                    }
                });
            }
        });



        return view;
    }

    private class ViewHolder{
        TextView text_name;
        TextView text_phone;
        TextView text_addr;
        CheckBox checkBox;
    }
}
