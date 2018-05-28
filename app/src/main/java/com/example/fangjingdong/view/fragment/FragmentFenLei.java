package com.example.fangjingdong.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.IHengMain;
import com.example.fangjingdong.view.activity.Select;
import com.example.fangjingdong.view.adapter.ListviewAdapter;
import com.example.fangjingdong.view.bean.HengBean;
import com.example.fangjingdong.view.presenter.Hengpre;
import com.example.fangjingdong.view.util.ChenJinUtil;
import com.example.fangjingdong.view.util.JieKou;

import java.util.List;

/**
 * Created by ASUS on 2018/1/23.
 */

public class FragmentFenLei extends Fragment implements IHengMain{

    private ListView fenlei_listview;
    private FrameLayout fenlei_frame;
    private Hengpre hengpre;
    private RelativeLayout fenlei_rel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fenlei,container,false);
        fenlei_listview = (ListView) view.findViewById(R.id.fenlei_listview);
        fenlei_frame = (FrameLayout) view.findViewById(R.id.fenlei_frame);
        fenlei_rel = (RelativeLayout) view.findViewById(R.id.fenlei_rel);
        fenlei_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Select.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initChenJin();

        hengpre = new Hengpre(this);
        hengpre.getDate(JieKou.FEN_LEI_URL);
    }



    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (! hidden) {
            initChenJin();
        }
    }
    private void initChenJin() {
        ChenJinUtil.setStatusBarColor(getActivity(), getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void IHengSuccess(final HengBean hengBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final List<HengBean.DataBean> data = hengBean.getData();
                final ListviewAdapter listviewAdapter = new ListviewAdapter(data, getActivity());
                fenlei_listview.setAdapter(listviewAdapter);
                int cid = data.get(0).getCid();
                FragmentFenLeiRight fragmentFenLeiRight =FragmentFenLeiRight.getCid(cid);
                getChildFragmentManager().beginTransaction().replace(R.id.fenlei_frame,fragmentFenLeiRight).commit();
                fenlei_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        listviewAdapter.setCurPositon(i);
                        listviewAdapter.notifyDataSetChanged();
                        fenlei_listview.smoothScrollToPositionFromTop(i,(adapterView.getHeight()-view.getHeight())/2);
                        int cid = data.get(i).getCid();
                        FragmentFenLeiRight fragmentFenLeiRight =FragmentFenLeiRight.getCid(cid);
                        getChildFragmentManager().beginTransaction().replace(R.id.fenlei_frame,fragmentFenLeiRight).commit();
                    }
                });
            }
        });
    }
}
