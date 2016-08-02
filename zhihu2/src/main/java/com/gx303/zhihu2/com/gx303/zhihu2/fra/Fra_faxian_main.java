package com.gx303.zhihu2.com.gx303.zhihu2.fra;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gx303.zhihu2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fra_faxian_main extends Fragment {


    public Fra_faxian_main() {
        // Required empty public constructor
    }

    TextView tv1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1=inflater.inflate(R.layout.fragment_faxian_main, container, false);
        tv1=(TextView)v1.findViewById(R.id.textView4);
        //获取Activity传递过来的参数
        Bundle mBundle = getArguments();
        String title = mBundle.getString("arg");
        tv1.setText(title);

        return v1;
    }


}
