package com.example.fangjingdong.view.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fangjingdong.R;
import com.example.fangjingdong.view.IView.IAddCartMain;
import com.example.fangjingdong.view.IView.IXiangMain;
import com.example.fangjingdong.view.bean.AddCartBean;
import com.example.fangjingdong.view.bean.XiangBean;
import com.example.fangjingdong.view.presenter.AddCart;
import com.example.fangjingdong.view.presenter.Xiangpre;
import com.example.fangjingdong.view.util.CommonUtils;
import com.example.fangjingdong.view.util.JieKou;
import com.example.fangjingdong.view.util.ShareUtil;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;

import static android.R.attr.data;

public class Xiangqing extends AppCompatActivity implements IXiangMain,IAddCartMain{

    private ViewPager xiang_vp;
    private TextView xiang_title;
    private TextView xiang_price;
    private TextView xiang_youhui;
    private Xiangpre xiangpre;
    private ImageView detail_image_back;
    private TextView jiaru;
    private AddCart addCart;
    private LinearLayout xiang_cart;
    private ImageView xiang_share;
    private XiangBean xiangBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        final String pid = getIntent().getStringExtra("pid");
        xiang_vp = (ViewPager) findViewById(R.id.xiang_vp);
        xiang_title = (TextView) findViewById(R.id.xiang_title);
        xiang_price = (TextView) findViewById(R.id.xiang_price);
        xiang_youhui = (TextView) findViewById(R.id.xiang_youhui);
        detail_image_back = (ImageView) findViewById(R.id.detail_image_back);
        jiaru = (TextView) findViewById(R.id.jiaru);
        xiang_cart = (LinearLayout) findViewById(R.id.xiang_cart);
        xiang_share = (ImageView) findViewById(R.id.xiang_share);
        //分享
        xiang_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xiangBean != null) {
                    //final Activity activity 分享的activity的上下文,
                    // String WebUrl 分享的商品的链接,
                    // String title 分享的商品的标题,
                    // String description 商品的描述,
                    // String imageUrl 商品的图片...如果没有图片传"",
                    // int imageID 本地商品的图片
                    XiangBean.DataBean data = xiangBean.getData();
                    ShareUtil.shareWeb(Xiangqing.this,data.getDetailUrl(), data.getTitle(),"我在京东发现一个好的商品,赶紧来看看吧!", data.getImages().split("\\|")[0],R.mipmap.ic_launcher);
                }

            }
        });
        detail_image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addCart = new AddCart(this);
        jiaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCart.getDate(JieKou.ADD_CART, CommonUtils.getString("uid"),pid);
            }
        });
        xiang_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Xiangqing.this, CartActivity.class);
                startActivity(intent);
            }
        });
        xiangpre = new Xiangpre(this);
        xiangpre.getDate(pid);
    }

    @Override
    public void XiangSuccess(final XiangBean xiangBean) {
        this.xiangBean=xiangBean;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                XiangBean.DataBean data = xiangBean.getData();
                String subhead = data.getSubhead();
                xiang_price.setText(subhead);
                double bargainPrice = data.getBargainPrice();
                xiang_youhui.setText("¥"+bargainPrice);
                xiang_title.setText(data.getTitle());
                String[] split = data.getImages().split("\\|");
                final ArrayList<String> imageurl=new ArrayList<String>();
                for (int i=0; i<split.length; i++){
                    imageurl.add(split[i]);
                }
                xiang_vp.setAdapter(new PagerAdapter() {
                    @Override
                    public int getCount() {
                        return imageurl.size();
                    }

                    @Override
                    public boolean isViewFromObject(View view, Object object) {
                        return view==object;
                    }

                    @Override
                    public Object instantiateItem(ViewGroup container, final int position) {
                        ImageView imageView=new ImageView(Xiangqing.this);
                        Glide.with(Xiangqing.this).load(imageurl.get(position)).into(imageView);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        container.addView(imageView);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Xiangqing.this, ChuKong.class);
                                intent.putStringArrayListExtra("list",imageurl);
                                intent.putExtra("position",position);
                                startActivity(intent);
                            }
                        });
                        return imageView;
                    }

                    @Override
                    public void destroyItem(ViewGroup container, int position, Object object) {
                        container.removeView((View) object);
                    }
                });
            }
        });
    }

    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Toast.makeText(Xiangqing.this,"分享开始",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(Xiangqing.this,"分享成功",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Log.e("----",throwable.getMessage());
            Toast.makeText(Xiangqing.this,"分享失败",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Toast.makeText(Xiangqing.this,"分享取消",Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    public void IAddCatSuccess(final AddCartBean addCartBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Xiangqing.this,addCartBean.getMsg(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
