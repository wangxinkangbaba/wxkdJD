package com.example.fangjingdong.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.FragmentCartInter;
import com.example.fangjingdong.view.IView.IBannerMain;
import com.example.fangjingdong.view.activity.Login;
import com.example.fangjingdong.view.activity.MyExpanableView;
import com.example.fangjingdong.view.activity.Querendingdan;
import com.example.fangjingdong.view.activity.Xiangqing;
import com.example.fangjingdong.view.IView.ClickListener;
import com.example.fangjingdong.view.adapter.MyExpanableAdapter;
import com.example.fangjingdong.view.adapter.TuiJianAdapter;
import com.example.fangjingdong.view.bean.BannerBean;
import com.example.fangjingdong.view.bean.CartBean;
import com.example.fangjingdong.view.bean.CountPriceBean;
import com.example.fangjingdong.view.presenter.Bannerpre;
import com.example.fangjingdong.view.presenter.FragmentCartPresenter;
import com.example.fangjingdong.view.util.ChenJinUtil;
import com.example.fangjingdong.view.util.CommonUtils;
import com.example.fangjingdong.view.util.JieKou;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/1/23.
 */

public class FragmentShopCart extends Fragment implements IBannerMain,FragmentCartInter,View.OnClickListener {
    private LinearLayout linear_login;
    private Button cart_login;
    private RecyclerView tui_jian_recycler;
    private MyExpanableView my_expanable_view;
    private RelativeLayout relative_progress;
    private CheckBox cart_check_all;
    private TextView text_total;
    private TextView text_buy;
    private ImageView empty_cart_image;
    private Bannerpre bannerpre;
    private FragmentCartPresenter fragmentCartPresenter;
    private MyExpanableAdapter myExpanableAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                CountPriceBean countPriceBean = (CountPriceBean) msg.obj;

                //设置显示
                text_total.setText("合计:¥"+countPriceBean.getPriceString());
                text_buy.setText("去结算("+countPriceBean.getCount()+")");
            }
        }
    };
    //创建一个集合,,,装的是去结算的时候 选中的所有商品的数据
    private ArrayList<CartBean.DataBean.ListBean> list_selected = new ArrayList<>();
    private CartBean cartBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shopcar,container,false);
        linear_login = view.findViewById(R.id.linear_login);
        cart_login = view.findViewById(R.id.cart_login);
        tui_jian_recycler = view.findViewById(R.id.tui_jian_recycler);
        my_expanable_view = view.findViewById(R.id.my_expanable_view);
        relative_progress = view.findViewById(R.id.relative_progress);
        cart_check_all = view.findViewById(R.id.cart_check_all);
        text_total = view.findViewById(R.id.text_total);
        text_buy = view.findViewById(R.id.text_buy);
        empty_cart_image = view.findViewById(R.id.empty_cart_image);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initChenJin();

        my_expanable_view.setGroupIndicator(null);

        //失去焦点....界面不是从recyclerView开始显示
        tui_jian_recycler.setFocusable(false);

        //2.为你推荐,,,只需要获取一次
        bannerpre = new Bannerpre(this);
        //调用p层里面的方法....https://www.zhaoapi.cn/ad/getAd
        bannerpre.getDate(JieKou.HOME_URL);
        fragmentCartPresenter = new FragmentCartPresenter(this);


        //全选 设置点击事件
        cart_check_all.setOnClickListener(this);
        text_buy.setOnClickListener(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //Log.e("----","隐藏");


        }else {
            //Log.e("----","显示");
            initChenJin();
            initData();

        }
    }

    private void initChenJin() {
        ChenJinUtil.setStatusBarColor(getActivity(), getResources().getColor(R.color.colorPrimaryDark));
    }

    private void initData() {
        if (CommonUtils.getBoolean("isLogin")) {
            //请求购物车的数据...显示购物车
            my_expanable_view.setVisibility(View.VISIBLE);
            linear_login.setVisibility(View.GONE);

            //请求购物车的数据
            getCartData();


        }else {
            //登录按钮的显示
            linear_login.setVisibility(View.VISIBLE);
            my_expanable_view.setVisibility(View.GONE);

            //登录的点击事件
            cart_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),Login.class);
                    startActivity(intent);
                }
            });

        }
    }

    private void getCartData() {
        relative_progress.setVisibility(View.VISIBLE);

        //查询购物车的数据
        fragmentCartPresenter.getCartData(JieKou.SELECT_CART,CommonUtils.getString("uid"));
    }

    @Override
    public void IBannerSucces(final BannerBean bannerBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final BannerBean.TuijianBean tuijian = bannerBean.getTuijian();
                tui_jian_recycler.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
                TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(tuijian, getActivity());
                tui_jian_recycler.setAdapter(tuiJianAdapter);
                tuiJianAdapter.OnItemClick(new ClickListener() {
                    @Override
                    public void OnitemClicklistener(int position) {
                        int pid = tuijian.getList().get(position).getPid();
                        Intent intent = new Intent(getActivity(), Xiangqing.class);
                        intent.putExtra("pid",pid+"");
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public void getCartDataNull() {
        relative_progress.setVisibility(View.GONE);
        my_expanable_view.setVisibility(View.GONE);
        empty_cart_image.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(),"购物车为空,先去逛逛吧", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCartDataSuccess(final CartBean cartBean) {
        this.cartBean=cartBean;
        relative_progress.setVisibility(View.GONE);
        empty_cart_image.setVisibility(View.GONE);
        my_expanable_view.setVisibility(View.VISIBLE);

        //显示购物车的数据....二级列表设置适配器

        //1.根据某一组里面所有子孩子是否选中,决定当前组是否选中
        try {
            for (int i =0;i<cartBean.getData().size();i++) {

                //遍历每一组的数据,,,设置是否选中...有所有的子孩子决定
                CartBean.DataBean dataBean = cartBean.getData().get(i);
                dataBean.setGroupChecked(isAllChildInGroupChecked(dataBean.getList()));
            }
        }catch (ArithmeticException e){

        }


        //2.根据所有的组是否选中,,,决定全选是否选中
        cart_check_all.setChecked(isAllGroupChecked(cartBean));

        //3.
        myExpanableAdapter = new MyExpanableAdapter(getActivity(), cartBean,handler,relative_progress,fragmentCartPresenter);
        my_expanable_view.setAdapter(myExpanableAdapter);

        //展开所有的每一组
        for (int i = 0;i<cartBean.getData().size();i++) {
            my_expanable_view.expandGroup(i);
        }

        //4.计算商品的总价和数量
        myExpanableAdapter.sendPriceAndCount();

        my_expanable_view.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {

//                Toast.makeText(getActivity(),"点击了",Toast.LENGTH_SHORT).show();

    //跳转的是自己的详情页面
    Intent intent = new Intent(getActivity(), Login.class);
    //传递pid
                intent.putExtra("pid",cartBean.getData().get(groupPosition).getList().get(childPosition).getPid());
    startActivity(intent);

                return false;
}
        });
                }

    private boolean isAllChildInGroupChecked(List<CartBean.DataBean.ListBean> list) {
        for (int i =0;i<list.size();i++) {
            if (list.get(i).getSelected() == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllGroupChecked(CartBean cartBean) {
        for (int i=0;i<cartBean.getData().size();i++) {
            //组没有选中
            if (! cartBean.getData().get(i).isGroupChecked()) {
                return false;
            }
        }
        return true;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cart_check_all://全选
                //点击全选的时候,,,,根据全选的状态 改变购物车所有商品的选中状态

                myExpanableAdapter.setAllChildChecked(cart_check_all.isChecked());
                break;
            case R.id.text_buy://去结算
                //当显示的是去结算(0)....打击没有任何反应
                if ("去结算(0)".equals(text_buy.getText().toString())) {
                    return;
                }

                //选中了商品之后,,,跳转到确认订单页面
                Intent intent = new Intent(getActivity(), Querendingdan.class);
                //跳转传值...选中的商品的数据...传递一个集合过去...选中的商品的集合

                //先清空一下集合
                list_selected.clear();

                for (int i = 0;i<cartBean.getData().size();i++) {
                    List<CartBean.DataBean.ListBean> list = cartBean.getData().get(i).getList();
                    for (int j = 0;j<list.size();j++) {

                        //判断是否选中,,,如果选中存放到集合中去
                        if (list.get(j).getSelected() == 1) {
                            list_selected.add(list.get(j));
                        }
                    }
                }
                //通过intent把集合传到下一个页面
                intent.putExtra("list_selected",list_selected);

                startActivity(intent);
                break;
        }
    }
}
