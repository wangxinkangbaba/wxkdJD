package com.example.fangjingdong.view.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fangjingdong.R;

import java.util.ArrayList;

public class ChuKong extends AppCompatActivity {

    private ViewPager chu_vp;
    private TextView chu_text;
    private ArrayList<String> list;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_kong);
        chu_vp = (ViewPager) findViewById(R.id.chu_vp);
        chu_text = (TextView) findViewById(R.id.chu_text);
        Intent intent = getIntent();
        list = intent.getStringArrayListExtra("list");
        position = intent.getIntExtra("position", -1);
        if (list !=null&& list.size()>0&& position !=-1){
            chu_text.setText((position +1)+"/"+ list.size());
            ImageurlAdapter imageurlAdapter = new ImageurlAdapter();
            chu_vp.setAdapter(imageurlAdapter);
            chu_vp.setCurrentItem(position,false);
            chu_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    chu_text.setText((position+1)+"/"+list.size());
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    private class ImageurlAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ZoomImageView zoomImageView = new ZoomImageView(ChuKong.this);
            Glide.with(ChuKong.this).load(list.get(position)).into(zoomImageView);
            container.addView(zoomImageView);
            return zoomImageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
