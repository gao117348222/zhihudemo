package com.gx303.zhihu2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.gx303.zhihu2.com.gx303.zhihu2.fra.Act_tiwen;
import com.gx303.zhihu2.com.gx303.zhihu2.fra.Fra_caogao;
import com.gx303.zhihu2.com.gx303.zhihu2.fra.Fra_faxian;
import com.gx303.zhihu2.com.gx303.zhihu2.fra.Fra_guanzhu;
import com.gx303.zhihu2.com.gx303.zhihu2.fra.Fra_shouchang;
import com.gx303.zhihu2.com.gx303.zhihu2.fra.Fra_shouye;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;


public class MainActivity extends ActionBarActivity {
    //声明相关变量
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView lvLeftMenu;
    private fr.castorflex.android.smoothprogressbar.SmoothProgressBar smoothProgressBar;
    private String[] lvs = {"首页", "发现", "关注", "收藏","草稿","提问"};
    private int[] lvs_res={R.drawable.ic_drawer_home,R.drawable.ic_drawer_explore,R.drawable.ic_drawer_follow,R.drawable.ic_drawer_collect,R.drawable.ic_drawer_draft,R.drawable.ic_drawer_question};
//    private ArrayAdapter arrayAdapter;
    main_left_listview_adapter adapter1;
    private AnimationDrawable mAnimationDrawable;
    private LinearLayout lay_rm;
    private Fragment main_fra;
//    private PullToRefreshLayout mPullToRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews(); //获取控件
        toolbar.setTitle(lvs[0]);//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
//        toolbar.setBackgroundColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                isMenuOpen=true;
                L("onDrawerOpened");
                //打开时把menu设置为默认只有查询和title是固定值
                toolbar.setTitle(R.string.titlename);
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                isMenuOpen=false;
                L("onDrawerClosed");
                //每当关闭时重新设置menu和title

                toolbar.setTitle(lvs[NowSelectIndex]);

                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //设置菜单列表
//        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs);
         adapter1=new main_left_listview_adapter(getApplicationContext(),lvs,lvs_res);
        lvLeftMenu.setAdapter(adapter1);
//        adapter1.notifyDataSetChanged();
//        lvLeftMenu.setSelection(0);
//        lvLeftMenu.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        lvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                T("点击了"+lvs[position]);


                closeLeftMenu();
                if(position!=5)//提问不是fragment，是activity弹出
                {
                    setNowSelectIndex(position);
                    adapter1.click(position);
                    adapter1.notifyDataSetInvalidated();
                    changeFragment(position);
                }
                else
                {
                    Intent it_tiwen=new Intent();
                    it_tiwen.setClass(getApplicationContext(), Act_tiwen.class);
//                    startActivity(it_tiwen);
                    openNewIntent(it_tiwen);
                }

            }
        });
        lay_rm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                closeLeftMenu();
                Intent it1=new Intent();
                it1.setClass(getApplicationContext(),userinfo.class);
//                startActivity(it1);
                openNewIntent(it1);

            }
        });
        s1.setLoadingcallback(loading_callback);
        s2.setLoadingcallback(loading_callback);
        s3.setLoadingcallback(loading_callback);
        s4.setLoadingcallback(loading_callback);
        changeFragment(0);
    }
    //关闭左侧菜单
    public void closeLeftMenu()
    {
        mDrawerLayout.closeDrawers();
    }
    private int NowSelectIndex=0;//现在选择的
    public void setNowSelectIndex(int i)
    {
        NowSelectIndex=i;
    }
    public void L(String s)
    {
        Log.e("Unity",s);
    }
    public void T(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
    private void findViews() {
//        ivRunningMan = (ImageView) findViewById(R.id.iv_main);
        toolbar = (Toolbar) findViewById(R.id.tl_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        lvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);
        lay_rm=(LinearLayout)findViewById(R.id.lay_rm);
        smoothProgressBar=(fr.castorflex.android.smoothprogressbar.SmoothProgressBar)findViewById(R.id.smoothloading);
//        mPullToRefreshLayout=((PullToRefreshLayout)findViewById(R.id.ptr_layout));
//        // Now setup the PullToRefreshLayout
//        ActionBarPullToRefresh.from(this)
//                // Mark All Children as pullable
//                .allChildrenArePullable()
//                        // Set a OnRefreshListener
//                .listener(onRefreshListene1)
//        // Finally commit the setup to our PullToRefreshLayout
//        .setup(mPullToRefreshLayout);

//        main_fra=(Fragment)findViewById(R.id.main_fragment);
    }
//    uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener onRefreshListene1=new uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener(){
//        @Override
//        public void onRefreshStarted(View view) {
//            Log.e("Unity","onRefreshStarted");
//        }
//    };
    Fra_shouye s1=new Fra_shouye();//首页
    Fra_faxian s2=new Fra_faxian();//发现
    Fra_guanzhu s3=new Fra_guanzhu();//关注
    Fra_shouchang s4=new Fra_shouchang();//收藏
    Fra_caogao s5=new Fra_caogao();//草稿
    public void changeFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position)
        {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.main_fragment,s1)
                        .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.main_fragment,s2)
                        .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.main_fragment,s3)
                        .commit();
                break;
            case 3:
                fragmentManager.beginTransaction()
                        .replace(R.id.main_fragment,s4)
                        .commit();
                break;
            case 4:
                fragmentManager.beginTransaction()
                        .replace(R.id.main_fragment,s5)
                        .commit();
                break;
//            case 5:
//                fragmentManager.beginTransaction()
//                        .replace(R.id.main_fragment,s5)
//                        .commit();
//                break;
        }


    }
    /*
     控制loading控件的显示
     */
    private main_loading_callback loading_callback=new main_loading_callback() {
        @Override
        public void loading(boolean b1) {
            if(b1)
            {
                smoothProgressBar.setVisibility(View.VISIBLE);
            }
            else
            {
                smoothProgressBar.setVisibility(View.GONE);
            }
        }
    };
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_search:
                    msg += "点击查找";
                    break;
                case R.id.action_nitify:
                    msg += "点击通知";
                    break;
                case R.id.action_edit:
                    msg+="点击编辑";
                    break;
                case R.id.action_shuffle:
                    msg+="点击随机看";
                    break;

            }

            if(!msg.equals("")) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };
    boolean isMenuOpen=false;//左侧菜单没有打开
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        L("onCreateOptionsMenu");
//     if(mDrawerToggle.)
        if(!isMenuOpen)
        {
            //没有打开状态，按照当前选择的页面来更改右上角按钮
            switch (NowSelectIndex)
            {
                case 0://首页
                case 2://关注
                case 3://收藏
                    getMenuInflater().inflate(R.menu.menu_main, menu);//搜索，消息
                    break;
                case 1://发现
                    getMenuInflater().inflate(R.menu.menu_main_faxian,menu);//随机看，搜索，消息
                    break;
                case 4://草稿
                    getMenuInflater().inflate(R.menu.menu_main_caogao,menu);//搜索，编辑，通知
                    break;
            }

        }
        else
        {
            //左侧菜单打开状态，需要按照当前页面来更改右上角按钮
            switch (NowSelectIndex)
            {
                case 0://首页
                case 2://关注
                case 3://收藏
                    getMenuInflater().inflate(R.menu.menu_main1, menu);//消息
                    break;
                case 1://发现
                    getMenuInflater().inflate(R.menu.menu_main_faxian_close,menu);//随机看，搜索
                    break;
                case 4://草稿
                    getMenuInflater().inflate(R.menu.menu_main_caogao_close,menu);//搜索，编辑
                    break;
            }

        }
        return true;
    }

    @Override
    public void onBackPressed() {
       //查看左侧菜单是否打开 打开的话先关闭
        if(isMenuOpen)
        {
            mDrawerLayout.closeDrawers();
        }
        else
        {
            System.exit(0);
        }

    }
    public void openNewIntent(Intent it1)
    {
        startActivity(it1);
        overridePendingTransition(R.anim.slide_right_in,0);

    }
}
