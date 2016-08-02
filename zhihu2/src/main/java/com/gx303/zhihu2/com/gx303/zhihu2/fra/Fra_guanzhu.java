package com.gx303.zhihu2.com.gx303.zhihu2.fra;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gx303.zhihu2.R;

/**
 * 关注
 */
public class Fra_guanzhu extends baseV4Fragment {


    public Fra_guanzhu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        L("关注");
        return inflater.inflate(R.layout.fragment_guanzhu, container, false);
    }


}
