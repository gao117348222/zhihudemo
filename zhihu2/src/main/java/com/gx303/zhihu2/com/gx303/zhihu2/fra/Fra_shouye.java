package com.gx303.zhihu2.com.gx303.zhihu2.fra;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gx303.zhihu2.R;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;


//首页
public class Fra_shouye extends baseV4Fragment {

    ListView l1;
    private PullToRefreshLayout mPullToRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        L("首页");
        View v1=inflater.inflate(R.layout.fragment_shouye, container, false);
        l1=(ListView)v1.findViewById(R.id.listView);
        mPullToRefreshLayout=((PullToRefreshLayout) v1.findViewById(R.id.ptr_layout));
        // Now setup the PullToRefreshLayout
        ActionBarPullToRefresh.from(getActivity())
                // Mark All Children as pullable
                .allChildrenArePullable()
                        // Set a OnRefreshListener
                .listener(onRefreshListene1)
        // Finally commit the setup to our PullToRefreshLayout
        .setup(mPullToRefreshLayout);
        getInfo();
        return v1;

    }
        uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener onRefreshListene1=new uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener(){
        @Override
        public void onRefreshStarted(View view) {
            Log.e("Unity", "onRefreshStarted");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //模拟网络请求需要的时间
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch (Exception e){}
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mPullToRefreshLayout.setRefreshComplete();
                        }
                    });

                }
            }).start();
        }
    };
    public void getInfo()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                loading();
                //模拟网络请求需要的时间
                try
                {
                    Thread.sleep(2000);
                }
                catch (Exception e){}
                hd1.sendEmptyMessage(0);
            }
        }).start();
    }

    Handler hd1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            loadingStop();
            String[] aa=new String[]{"测试1","测试2","test3","test4","test5","test6","test7","test8","test9","test10"};
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,aa);
            l1.setAdapter(adapter);
        }
    };


}
