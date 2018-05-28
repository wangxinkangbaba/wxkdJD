package com.example.fangjingdong.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.IBannerMain;
import com.example.fangjingdong.view.IView.IHengMain;
import com.example.fangjingdong.view.activity.CustomCaptrueActivity;
import com.example.fangjingdong.view.activity.Goods;
import com.example.fangjingdong.view.activity.MainActivity;
import com.example.fangjingdong.view.activity.PaoMaDeng;
import com.example.fangjingdong.view.activity.Select;
import com.example.fangjingdong.view.activity.Web;
import com.example.fangjingdong.view.activity.Xiangqing;
import com.example.fangjingdong.view.IView.ClickListener;
import com.example.fangjingdong.view.adapter.HengAdapter;
import com.example.fangjingdong.view.adapter.MiaoShaAdapter;
import com.example.fangjingdong.view.adapter.TuiJianAdapter;
import com.example.fangjingdong.view.bean.BannerBean;
import com.example.fangjingdong.view.bean.HengBean;
import com.example.fangjingdong.view.bean.PaoMaBean;
import com.example.fangjingdong.view.liushi.ObservableScrollView;
import com.example.fangjingdong.view.presenter.Bannerpre;
import com.example.fangjingdong.view.presenter.Hengpre;
import com.example.fangjingdong.view.util.ChenJinUtil;
import com.example.fangjingdong.view.util.JieKou;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.Banner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/1/23.
 */

public class FragmentHome extends Fragment implements IBannerMain,IHengMain{

    private Banner ban;
    private Bannerpre bannerpre;
    private List<String> imageUrl;
    private RecyclerView heng_rev;
    private Hengpre hengpre;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            countDown();
            sendEmptyMessageDelayed(0, 1000);
        }
    };
    private RecyclerView miaosha_rev;
    private RecyclerView tuijian_rev;
    private PaoMaDeng paomadeng;
    private RelativeLayout sousuo;
    private LinearLayout linear_include;
    private ObservableScrollView observe_scroll_view;
    private final String TAG_MARGIN_ADDED = "marginAdded";
    private LinearLayout sao;
    private SmartRefreshLayout smart_refresh;
    private int height;
    private ObservableScrollView obserView;
    private TextView tv_miaosha_time;
    private TextView tv_miaosha_shi;
    private TextView tv_miaosha_minter;
    private TextView tv_miaosha_second;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shouye,container,false);
        ban = (Banner) view.findViewById(R.id.ban);
        heng_rev = (RecyclerView) view.findViewById(R.id.heng_rev);
        miaosha_rev = (RecyclerView) view.findViewById(R.id.miaosha_rev);
        tuijian_rev = (RecyclerView) view.findViewById(R.id.tuijian_rev);
        paomadeng = (PaoMaDeng) view.findViewById(R.id.paomadeng);
        sousuo = (RelativeLayout) view.findViewById(R.id.sousuo);
        linear_include = view.findViewById(R.id.linear_include);
        observe_scroll_view = view.findViewById(R.id.observe_scroll_view);
        sao = (LinearLayout) view.findViewById(R.id.saoyisao);
        smart_refresh = view.findViewById(R.id.smart_refresh);
        tv_miaosha_time = view.findViewById(R.id.tv_miaosha_time);
        tv_miaosha_shi = view.findViewById(R.id.tv_miaosha_shi);
        tv_miaosha_minter = view.findViewById(R.id.tv_miaosha_minter);
        tv_miaosha_second = view.findViewById(R.id.tv_miaosha_second);
        ArrayList<PaoMaBean> paoma=new ArrayList<>();
        paoma.add(new PaoMaBean("推荐","国货PK美国货,结果让人震惊", "连接1"));
        paoma.add(new PaoMaBean("推荐","这次XiPhone,可能让你迷路", "连接2"));
        paoma.add(new PaoMaBean("HOT", "为什么吉普,奥巴马都爱钓鱼", "连接3"));
        paoma.add(new PaoMaBean("HOT", "虽然我字难看,但我钢笔好看啊", "连接4"));
        paomadeng.setFrontColor(Color.RED);
        paomadeng.setBackColor(Color.BLACK);
        paomadeng.setmTexts(paoma);
        paomadeng.setmDuration(1000);
        paomadeng.setmInterval(1000);
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Select.class);
                startActivity(intent);
            }
        });



        sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 1001);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        startCountDown();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ChenJinUtil.setStatusBarColor(getActivity(),Color.TRANSPARENT);
        //标题栏
        initTitleBar();
        bannerpre = new Bannerpre(this);
        bannerpre.getDate(JieKou.HOME_URL);
        hengpre = new Hengpre(this);
        hengpre.getDate(JieKou.FEN_LEI_URL);

        smart_refresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                smart_refresh.finishLoadmore(2000);
            }
        });
        smart_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smart_refresh.finishRefresh(2000);
            }
        });
    }

    private void initTitleBar() {
        addMargin();
        ViewTreeObserver viewTreeObserver = linear_include.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linear_include.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = linear_include.getHeight();
                observe_scroll_view.setScrollViewListener(new ObservableScrollView.IScrollViewListener() {
                    @Override
                    public void onScrollChanged(int x, int y, int oldx, int oldy) {
                        if (y <= 0) {
                            addMargin();
                            ChenJinUtil.setStatusBarColor(getActivity(),Color.TRANSPARENT);
                            linear_include.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或
                        } else if (y > 0 && y < height) {
                            if (y > ChenJinUtil.getStatusBarHeight(getActivity())) {
                                //去掉margin
                                removeMargin();
                                //状态栏为灰色
                                ChenJinUtil.setStatusBarColor(getActivity(),getResources().getColor(R.color.colorPrimaryDark));
                            }

                            float scale = (float) y / height;
                            float alpha = (255 * scale);
                            // 只是layout背景透明(仿知乎滑动效果)
                            linear_include.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                        } else {
                            linear_include.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                        }

                    }
                });
            }
        });
    }
    private void addMargin() {
        if (!TAG_MARGIN_ADDED.equals(linear_include.getTag())) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) linear_include.getLayoutParams();
            // 添加的间隔大小就是状态栏的高度
            params.topMargin += ChenJinUtil.getStatusBarHeight(getActivity());
            linear_include.setLayoutParams(params);
            linear_include.setTag(TAG_MARGIN_ADDED);
        }
    }

    private void removeMargin() {
        if (TAG_MARGIN_ADDED.equals(linear_include.getTag())) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) linear_include.getLayoutParams();
            // 移除的间隔大小就是状态栏的高度
            params.topMargin -= ChenJinUtil.getStatusBarHeight(getActivity());
            linear_include.setLayoutParams(params);
            linear_include.setTag(null);
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (! hidden) {

            //可见的时候...当前位置>stusbar
            if (observe_scroll_view.getScrollY() > ChenJinUtil.getStatusBarHeight(getActivity())) {

                ChenJinUtil.setStatusBarColor(getActivity(),getResources().getColor(R.color.colorPrimaryDark));
            }else {

                ChenJinUtil.setStatusBarColor(getActivity(),Color.TRANSPARENT);
            }
        }
    }

    @Override
    public void IBannerSucces(final BannerBean bannerBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final List<BannerBean.DataBean> list = bannerBean.getData();
                imageUrl = new ArrayList<String>();
                for (int i=0; i<list.size(); i++){
                    String icon = list.get(i).getIcon();
                    imageUrl.add(icon);
                }
                ban.setBannerStyle(Banner.AUTOFILL_TYPE_LIST);
                ban.setIndicatorGravity(Banner.CENTER);
                ban.setDelayTime(3000);
                ban.isAutoPlay(true);
                ban.setImages(imageUrl);
                ban.setOnBannerClickListener(new Banner.OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(View view, int position) {
                        if (list.get(position).getType()==0){
                            Intent intent = new Intent(getActivity(), Web.class);
                            intent.putExtra("detailUrl",list.get(position).getUrl());
                            startActivity(intent);
                        }
                    }
                });
                //秒杀
                final BannerBean.MiaoshaBean miaosha = bannerBean.getMiaosha();
                miaosha_rev.setLayoutManager(new LinearLayoutManager(getActivity(),OrientationHelper.HORIZONTAL,true));
                MiaoShaAdapter miaoShaAdapter = new MiaoShaAdapter(miaosha, getActivity());
                miaosha_rev.setAdapter(miaoShaAdapter);
                miaoShaAdapter.OnItemClick(new ClickListener() {
                    @Override
                    public void OnitemClicklistener(int position) {
                        Intent intent = new Intent(getActivity(), Web.class);

                        String detailUrl = bannerBean.getMiaosha().getList().get(position).getDetailUrl();
                        intent.putExtra("detailUrl",detailUrl);
                        startActivity(intent);
                    }
                });
                //推荐
                final BannerBean.TuijianBean tuijian = bannerBean.getTuijian();
                tuijian_rev.setLayoutManager(new StaggeredGridLayoutManager(2,OrientationHelper.VERTICAL));
                TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(tuijian, getActivity());
                tuijian_rev.setAdapter(tuiJianAdapter);
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
    public void IHengSuccess(final HengBean hengBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final List<HengBean.DataBean> heng = hengBean.getData();
                heng_rev.setLayoutManager(new GridLayoutManager(getActivity(),2, OrientationHelper.HORIZONTAL,true));
                HengAdapter hengAdapter = new HengAdapter(heng,getActivity());
                heng_rev.setAdapter(hengAdapter);
                hengAdapter.OnItemClick(new ClickListener() {
                    @Override
                    public void OnitemClicklistener(int position) {
                        Intent intent = new Intent(getActivity(), Goods.class);
                        String name = heng.get(position).getName();
                        intent.putExtra("keywords",name);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                //拿到传递回来的数据
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }

                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    //解析得到的结果
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    if (result.startsWith("http://")) {
                        Intent intent = new Intent(getActivity(),Web.class);
                        intent.putExtra("detailUrl",result);
                        startActivity(intent);
                    }else {

                        Toast.makeText(getActivity(), "暂不支持此二维码", Toast.LENGTH_LONG).show();
                    }
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void countDown() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String format = df.format(curDate);
        StringBuffer buffer = new StringBuffer();
        String substring = format.substring(0, 11);
        buffer.append(substring);
        Log.d("ccc", substring);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour % 2 == 0) {
            tv_miaosha_time.setText(hour + "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");
        } else {
            tv_miaosha_time.setText((hour - 1) + "点场");
            buffer.append((hour + 1));
            buffer.append(":00:00");
        }
        String totime = buffer.toString();
        try {
            java.util.Date date = df.parse(totime);
            java.util.Date date1 = df.parse(format);
            long defferenttime = date.getTime() - date1.getTime();
            long days = defferenttime / (1000 * 60 * 60 * 24);
            long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = defferenttime % 60000;
            long second = Math.round((float) seconds / 1000);
            tv_miaosha_shi.setText("0" + hours + "");
            if (minute >= 10) {
                tv_miaosha_minter.setText(minute + "");
            } else {
                tv_miaosha_minter.setText("0" + minute + "");
            }
            if (second >= 10) {
                tv_miaosha_second.setText(second + "");
            } else {
                tv_miaosha_second.setText("0" + second + "");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void startCountDown() {
        handler.sendEmptyMessage(0);
    }
}
