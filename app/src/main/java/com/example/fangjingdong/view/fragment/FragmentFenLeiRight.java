package com.example.fangjingdong.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.IFenMain;
import com.example.fangjingdong.view.adapter.ZiFenAdapter;
import com.example.fangjingdong.view.bean.ZiFenBean;
import com.example.fangjingdong.view.presenter.FenPre;
import com.example.fangjingdong.view.util.JieKou;

import java.util.List;

/**
 * Created by ASUS on 2018/1/25.
 */

public class FragmentFenLeiRight extends Fragment implements IFenMain{

    private RecyclerView fenleiright_rev;
    private FenPre fenPre;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentfenleiright_layout,container,false);
        int cid = getArguments().getInt("cid");
        fenleiright_rev = (RecyclerView) view.findViewById(R.id.fenleiright_rev);
        fenPre = new FenPre(this);
        fenPre.getDate(JieKou.CHILD_FEN_LEI_URL,cid+"");
        return view;
    }



    public static FragmentFenLeiRight getCid(int cid){
        FragmentFenLeiRight fragmentFenLeiRight = new FragmentFenLeiRight();
        Bundle bundle = new Bundle();
        bundle.putInt("cid",cid);
        fragmentFenLeiRight.setArguments(bundle);
        return fragmentFenLeiRight;
    }

    @Override
    public void FenSuccess(final ZiFenBean ziFenBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ZiFenBean.DataBean> list = ziFenBean.getData();
                fenleiright_rev.setAdapter(new ZiFenAdapter(list,getContext()));
                fenleiright_rev.setLayoutManager(new GridLayoutManager(getActivity(),1, LinearLayoutManager.VERTICAL,false));
            }
        });
    }


}
