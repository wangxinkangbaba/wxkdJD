package com.example.fangjingdong.view.application;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Process;
import android.view.View;


import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Dash on 2018/1/23.
 */
public class DashApplication extends Application{

    private static int myTid;
    private static Handler handler;
    private static Context context;


    /**
     * 实际开发中 这些平台的id,key,secret全都是要去对应的开放平台申请
     */
    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        myTid = Process.myTid();
        handler = new Handler();
        context = getApplicationContext();

        //初始化自己的异常捕获机制
//        CrashHandler.getInstance().init(this);

        //如果要使用腾讯的bugly处理异常...必须把自己的异常处理给注释掉

        //初始化友盟
        UMShareAPI.get(this);
        Config.DEBUG = true;//开启debug
        //二维码初始化
        ZXingLibrary.initDisplayOpinion(this);

    }

    private void setDatebase() {

    }



    //获取主线程的id
    public static int getMainThreadId() {

        return myTid;
    }

    //获取handler
    public static Handler getAppHanler() {
        return handler;
    }

    public static Context getAppContext() {
        return context;
    }


}
