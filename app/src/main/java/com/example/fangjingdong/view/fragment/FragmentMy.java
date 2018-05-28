package com.example.fangjingdong.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.IBannerMain;
import com.example.fangjingdong.view.activity.Dingdan;
import com.example.fangjingdong.view.activity.Login;
import com.example.fangjingdong.view.activity.MyDingDan;
import com.example.fangjingdong.view.activity.Xiangqing;
import com.example.fangjingdong.view.activity.Zhanghushezhi;
import com.example.fangjingdong.view.IView.ClickListener;
import com.example.fangjingdong.view.adapter.TuiJianAdapter;
import com.example.fangjingdong.view.bean.BannerBean;
import com.example.fangjingdong.view.liushi.ObservableScrollView;
import com.example.fangjingdong.view.presenter.Bannerpre;
import com.example.fangjingdong.view.util.ChenJinUtil;
import com.example.fangjingdong.view.util.CommonUtils;
import com.example.fangjingdong.view.util.GlideImgManager;
import com.example.fangjingdong.view.util.JieKou;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import static com.example.fangjingdong.R.id.fragment_my_scroll;
import static com.example.fangjingdong.R.id.my_text;
import static com.example.fangjingdong.R.id.observe_scroll_view;

/**
 * Created by ASUS on 2018/1/23.
 */

public class FragmentMy extends Fragment implements IBannerMain, View.OnClickListener {

    private RecyclerView tui_jian_recycler;
    private Bannerpre bannerpre;
    private SmartRefreshLayout my_srl;
    private RelativeLayout login_back_pic;
    private ImageView my_user_icon;
    private TextView my_user_name;
    private LinearLayout my_order_dfk;
    private LinearLayout my_order_dpj;
    private LinearLayout my_order_dsh;
    private LinearLayout my_order_th;
    private LinearLayout my_order_all;
    private LinearLayout my_linear_login;
    private ObservableScrollView fragment_my_scroll;
    private ImageView my_img;
    private RelativeLayout relative_include;
    private int height;
    private final String TAG_MARGIN_ADDED = "marginAdded";
    private TextView my_text;
    private ImageView my_xx1;
    private ImageView my_sz;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my,container,false);
        tui_jian_recycler = (RecyclerView) view.findViewById(R.id.tui_jian_recycler);
        my_srl = (SmartRefreshLayout) view.findViewById(R.id.my_srl);
        login_back_pic = view.findViewById(R.id.login_back_pic);
        my_user_icon = view.findViewById(R.id.my_user_icon);
        my_user_name = view.findViewById(R.id.my_user_name);
        my_order_dfk = view.findViewById(R.id.my_order_dfk);
        my_order_dpj = view.findViewById(R.id.my_order_dpj);
        my_order_dsh = view.findViewById(R.id.my_order_dsh);
        my_order_th = view.findViewById(R.id.my_order_th);
        my_order_all = view.findViewById(R.id.my_order_all);
        my_linear_login = view.findViewById(R.id.my_linear_login);
        my_img = (ImageView) view.findViewById(R.id.my_img);
        relative_include = (RelativeLayout) view.findViewById(R.id.relative_include);
        fragment_my_scroll = view.findViewById(R.id.fragment_my_scroll);
        my_text = (TextView) view.findViewById(R.id.my_text);
        my_xx1 = (ImageView) view.findViewById(R.id.my_xx1);
        my_sz = (ImageView) view.findViewById(R.id.my_sz);
        my_linear_login.setOnClickListener(this);
        my_order_dfk.setOnClickListener(this);
        my_order_dpj.setOnClickListener(this);
        my_order_dsh.setOnClickListener(this);
        my_order_th.setOnClickListener(this);
        bannerpre = new Bannerpre(this);
        bannerpre.getDate(JieKou.HOME_URL);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initChenJin();
        initTitleBar();
        my_img.setVisibility(View.GONE);
        my_xx1.setVisibility(View.GONE);
        my_text.setVisibility(View.GONE);
        my_sz.setVisibility(View.GONE);
       my_srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                my_srl.finishRefresh(2000);
            }
        });
        my_srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                my_srl.finishLoadmore(2000);
            }
        });

    }

    private void initTitleBar() {
        addMargin();
        ViewTreeObserver viewTreeObserver = relative_include.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                relative_include.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = relative_include.getHeight();
                fragment_my_scroll.setScrollViewListener(new ObservableScrollView.IScrollViewListener() {
                    @Override
                    public void onScrollChanged(int x, int y, int oldx, int oldy) {
                        if (y <= 0) {
                            addMargin();
                            my_img.setVisibility(View.GONE);
                            my_xx1.setVisibility(View.GONE);
                            my_text.setVisibility(View.GONE);
                            my_sz.setVisibility(View.GONE);
                            ChenJinUtil.setStatusBarColor(getActivity(),Color.TRANSPARENT);
                            relative_include.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或
                        } else if (y > 0 && y < height) {
                            if (y > ChenJinUtil.getStatusBarHeight(getActivity())) {
                                //去掉margin
                                removeMargin();
                                //状态栏为灰色
                                ChenJinUtil.setStatusBarColor(getActivity(),getResources().getColor(R.color.colorPrimaryDark));
                            }
                            my_img.setVisibility(View.GONE);
                            my_xx1.setVisibility(View.GONE);
                            my_text.setVisibility(View.GONE);
                            my_sz.setVisibility(View.GONE);
                            float scale = (float) y / height;
                            float alpha = (255 * scale);
                            // 只是layout背景透明(仿知乎滑动效果)
                            relative_include.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                        } else {
                            relative_include.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                            my_img.setVisibility(View.VISIBLE);
                            my_xx1.setVisibility(View.VISIBLE);
                            my_text.setVisibility(View.VISIBLE);
                            my_sz.setVisibility(View.VISIBLE);
                        }

                    }
                });
            }
        });
    }

    private void removeMargin() {
        if (TAG_MARGIN_ADDED.equals(relative_include.getTag())) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) relative_include.getLayoutParams();
            // 移除的间隔大小就是状态栏的高度
            params.topMargin -= ChenJinUtil.getStatusBarHeight(getActivity());
            relative_include.setLayoutParams(params);
            relative_include.setTag(null);
        }
    }

    private void addMargin() {
        if (!TAG_MARGIN_ADDED.equals(relative_include.getTag())) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) relative_include.getLayoutParams();
            // 添加的间隔大小就是状态栏的高度
            params.topMargin += ChenJinUtil.getStatusBarHeight(getActivity());
            relative_include.setLayoutParams(params);
            relative_include.setTag(TAG_MARGIN_ADDED);
        }
    }

    private void initChenJin() {
        ChenJinUtil.setStatusBarColor(getActivity(), Color.TRANSPARENT);
    }

    @Override
    public void onResume() {
        super.onResume();
        fragment_my_scroll.smoothScrollBy(0,0);
        initDate();
    }

    private void initDate() {
        boolean isLogin = CommonUtils.getBoolean("isLogin");
        if (isLogin) {
            if ( "".equals(CommonUtils.getString("iconUrl"))  || "null".equals(CommonUtils.getString("iconUrl"))){
                //显示默认头像
                my_user_icon.setImageResource(R.drawable.user);
                my_img.setImageResource(R.drawable.user);
            }else {

                //1.加载一下头像显示...判断一下是否有头像的路径,,,没有则显示默认的头像
//                Glide.with(getActivity()).load(CommonUtils.getString("iconUrl")).into(my_user_icon);
                GlideImgManager.glideLoader(getActivity(), CommonUtils.getString("iconUrl"), R.drawable.user, R.drawable.user, my_user_icon, 0);
                GlideImgManager.glideLoader(getActivity(), CommonUtils.getString("iconUrl"), R.drawable.user, R.drawable.user, my_img, 0);
            }
            //2.先试一下用户名
            my_user_name.setText(CommonUtils.getString("name"));

            //背景图片的切换
            login_back_pic.setBackgroundResource(R.drawable.reg_bg);
        }else {
            //显示默认头像
            my_user_icon.setImageResource(R.drawable.user);
            my_img.setImageResource(R.drawable.user);
            //用户名显示 登录/注册 >
            my_user_name.setText("登录/注册 >");

            //背景图片的切换
            login_back_pic.setBackgroundResource(R.drawable.normal_regbg);

        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (! hidden) {//可见
            //scrollView滚动到最上面
            fragment_my_scroll.smoothScrollTo(0,0);
            if (fragment_my_scroll.getScrollY() > ChenJinUtil.getStatusBarHeight(getActivity())) {

                ChenJinUtil.setStatusBarColor(getActivity(),getResources().getColor(R.color.colorPrimaryDark));
            }else {

                ChenJinUtil.setStatusBarColor(getActivity(),Color.TRANSPARENT);
            }
            initDate();
        }
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
    public void onClick(View view) {
        boolean isLogin = CommonUtils.getBoolean("isLogin");

        Intent intent = new Intent();

        if (! isLogin) {
            //跳转登录注册页面
            intent.setClass(getActivity(),Login.class);

        }else {
            switch (view.getId()) {

                case R.id.my_linear_login://跳转账户设置页面
                    intent.setClass(getActivity(), Zhanghushezhi.class);

                    break;
                case R.id.my_order_dfk://我的订单...代付款
                    //可以传值过去...显示哪一个tablayout
                    intent.setClass(getActivity(), MyDingDan.class);
                    intent.putExtra("flag",1);

                    break;
                case R.id.my_order_dpj://待评价

                    intent.setClass(getActivity(), MyDingDan.class);
                    intent.putExtra("flag",2);


                    break;
                case R.id.my_order_dsh://待收货

                    intent.setClass(getActivity(), MyDingDan.class);
                    intent.putExtra("flag",3);
                    break;
                case R.id.my_order_th://退化

                    intent.setClass(getActivity(), MyDingDan.class);

                    break;

            }
        }

        //开启
        startActivity(intent);

    }

}
