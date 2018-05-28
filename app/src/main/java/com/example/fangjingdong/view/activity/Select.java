package com.example.fangjingdong.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fangjingdong.R;
import com.example.fangjingdong.view.liushi.ZFlowLayout;
import com.example.fangjingdong.view.shujuku.MyDao;

import java.util.List;

public class Select extends AppCompatActivity {

    private ImageView select_fan;
    private TextView select_sou;
    private EditText select_edsou;
    private ZFlowLayout mFlowLayout;
    private ListView list_lishi;
    private LinearLayout linear_lishi;
    private Button qinkong;
    private String[] mLabels = {"硬盘螺丝","老人机","游玩","北京","CSDN"};
    private MyDao dao;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        select_fan = (ImageView) findViewById(R.id.select_fan);
        select_sou = (TextView) findViewById(R.id.select_sou);
        select_edsou = (EditText) findViewById(R.id.select_edsou);
        mFlowLayout = (ZFlowLayout) findViewById(R.id.flowLayout);
        list_lishi = (ListView) findViewById(R.id.list_lishi);
        linear_lishi = (LinearLayout) findViewById(R.id.linear_lishi);
        qinkong = (Button) findViewById(R.id.qingkong);
        initLabel();
        dao = new MyDao(Select.this);
        list = dao.select();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Select.this, android.R.layout.simple_expandable_list_item_1, list);
        list_lishi.setAdapter(adapter);
        select_sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = select_edsou.getText().toString();
                if (s!=null&&s!=""){
                    int i = dao.addtian(s);
                    if (i==1){
                        linear_lishi.setVisibility(View.VISIBLE);
                        adapter();
                    }
                    if (list.size()>0){
                        linear_lishi.setVisibility(View.VISIBLE);
                    }else {
                        linear_lishi.setVisibility(View.GONE);
                    }
                    Intent intent = new Intent(Select.this, Goods.class);
                    intent.putExtra("keywords",s);
                    startActivity(intent);
                }
            }
        });
        list_lishi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Select.this);
                builder.setTitle("是否删除？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int delet = dao.delet(list.get(position));
                        if (delet==1){
                            adapter();
                        }
                        if (list.size()>0){
                            linear_lishi.setVisibility(View.VISIBLE);
                        }else {
                            linear_lishi.setVisibility(View.GONE);
                        }

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return false;
            }
        });
        if (list.size()>0){
            linear_lishi.setVisibility(View.VISIBLE);
        }else {
            linear_lishi.setVisibility(View.GONE);
        }

        qinkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.delete();
                linear_lishi.setVisibility(View.GONE);
            }
        });
        select_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initLabel() {
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 30, 10, 10);// 设置边距
        for (int i = 0; i < mLabels.length; i++) {
            final TextView textView = new TextView(Select.this);
            textView.setTag(i);
            textView.setTextSize(15);
            textView.setText(mLabels[i]);
            textView.setPadding(24, 11, 24, 11);
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundResource(R.drawable.lable_item_bg_normal);
            mFlowLayout.addView(textView, layoutParams);
            // 标签点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), mLabels[(int) textView.getTag()], Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void adapter(){
        List<String> list=dao.select();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Select.this, android.R.layout.simple_expandable_list_item_1, list);
        list_lishi.setAdapter(adapter);
    }
}
