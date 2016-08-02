package com.gx303.zhihu2.com.gx303.zhihu2.fra;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.gx303.zhihu2.R;
//import com.viewpagerindicator.TabPageIndicator;
//import com.viewpagerindicator.TitlePageIndicator;

//发现
public class Fra_faxian extends baseV4Fragment {

    ViewPager vp1;
//    TabPageIndicator titleindicator;
    private static final String[] TITLE = new String[] { "热门推荐", "热门收藏", "本月热榜", "今日热榜" };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        L("发现111");
        View v1=inflater.inflate(R.layout.fragment_faxian, container, false);
        vp1=(ViewPager)v1.findViewById(R.id.vPager);
//        titleindicator=(TabPageIndicator)v1.findViewById(R.id.titles);

        adapter adapter1=new adapter(getFragmentManager());
        vp1.setAdapter(adapter1);

//        titleindicator.setViewPager(vp1);
// Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) v1.findViewById(R.id.tabs);
        tabs.setViewPager(vp1);


        return v1;
    }

      class adapter extends FragmentPagerAdapter
      {
          public adapter(FragmentManager  fm)
          {
              super(fm);
          }
          @Override
          public Fragment getItem(int position) {
              Fra_faxian_main fragment=new Fra_faxian_main();
              Bundle args = new Bundle();
              args.putString("arg", TITLE[position]);
              fragment.setArguments(args);

              return fragment;
          }

          @Override
          public int getCount() {
              return TITLE.length;
          }

          @Override
          public CharSequence getPageTitle(int position) {
              return TITLE[position % TITLE.length];
          }
      }

}
