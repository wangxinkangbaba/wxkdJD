package com.example.fangjingdong.view.bean;

/**
 * Created by ASUS on 2018/1/26.
 */

public class PaoMaBean {
    private String mFront ; //前面的文字
    private String mBack ; //后面的文字
    private String mUrl ;//包含的链接

    public PaoMaBean(String mFront, String mBack, String mUrl) {
        this.mFront = mFront;
        this.mBack = mBack;
        this.mUrl = mUrl;
    }

    public void setmFront(String mFront) {
        this.mFront = mFront;
    }

    public void setmBack(String mBack) {
        this.mBack = mBack;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmFront() {
        return mFront;
    }

    public String getmBack() {
        return mBack;
    }

    public String getmUrl() {
        return mUrl;
    }
}
