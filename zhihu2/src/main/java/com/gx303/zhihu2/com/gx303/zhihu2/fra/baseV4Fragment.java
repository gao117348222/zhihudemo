package com.gx303.zhihu2.com.gx303.zhihu2.fra;

import android.util.Log;

import com.gx303.zhihu2.main_loading_callback;

/**
 * Created by Administrator on 2015/3/17.
 */
public class baseV4Fragment  extends android.support.v4.app.Fragment  {
    private main_loading_callback loadingcallback;
    public void setLoadingcallback(main_loading_callback c1)
    {
        loadingcallback=c1;
    }
    public void loading()
    {
        loadingcallback.loading(true);
    }
    public void loadingStop()
    {
        loadingcallback.loading(false);
    }
    public void L(String s)
    {
        Log.e("Unity",s);
    }
}
