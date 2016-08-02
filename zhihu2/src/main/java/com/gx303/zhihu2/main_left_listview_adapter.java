package com.gx303.zhihu2;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2015/3/14.
 */
public class main_left_listview_adapter extends BaseAdapter {
    public static class ViewHolder
    {
        public TextView tv1;
        public ImageView iv1;
        public LinearLayout ly1;
    }
    private LayoutInflater mInflater;
      private Context mContext;

    String[] l1;
    int[] l2;
    int nowSelectPosition=0;
    public  main_left_listview_adapter(Context context,String[] list1,int[] list2)
    {
        mInflater=LayoutInflater.from(context);
        mContext=context;
        l1=list1;
        l2=list2;
    }
    public void click(int p)
    {
        nowSelectPosition=p;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return l1.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh1=null;
        if(convertView==null)
        {
            vh1=new ViewHolder();
            convertView=mInflater.inflate(R.layout.main_left_listview_item,parent,false);
            vh1.tv1=(TextView)convertView.findViewById(R.id.tv1);
            vh1.iv1=(ImageView)convertView.findViewById(R.id.iv1);
            vh1.ly1=(LinearLayout)convertView.findViewById(R.id.ly1);
            convertView.setTag(vh1);

        }
        else
        {
            vh1=(ViewHolder)convertView.getTag();
        }
        vh1.tv1.setText(l1[position]);
        vh1.iv1.setImageResource(l2[position]);
        if(position==nowSelectPosition)
        {
            vh1.ly1.setActivated(true);
            vh1.iv1.setActivated(true);
            vh1.tv1.setActivated(true);
        }
        else
        {
            vh1.ly1.setActivated(false);
            vh1.iv1.setActivated(false);
            vh1.tv1.setActivated(false);
        }
        return convertView;
    }
//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//        v.setSelected(true);
//        //TODO: add further actions here
//    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
}
