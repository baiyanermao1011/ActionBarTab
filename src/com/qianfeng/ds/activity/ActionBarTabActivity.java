package com.qianfeng.ds.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by Administrator on 2015/1/22.
 */
public class ActionBarTabActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, ActionBar.TabListener {

    private int position;
    private ViewPager pager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_tab);
        pager = (ViewPager)findViewById(R.id.tab_pager);
        Intent intent=getIntent();
        position =intent.getIntExtra("position",0);
        List<String> noteList = DataStore.getInstance().getNoteList();
        NoteFragmentAdapter adapter=new NoteFragmentAdapter(getSupportFragmentManager(),noteList);
        pager.setAdapter(adapter);

        pager.setOnPageChangeListener(this);



        //创建ActionBar的导航模式
        ActionBar actionBar=getActionBar();
        if(actionBar!=null){
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            ActionBar.Tab tab = actionBar.newTab();
            tab.setText("热点").setTag("1");
            tab.setTabListener(this);
            actionBar.addTab(tab);
            tab = actionBar.newTab();
            tab.setText("娱乐").setTag("2");
            tab.setTabListener(this);
            actionBar.addTab(tab);
            tab = actionBar.newTab();
            tab.setText("体育").setTag("3");
            tab.setTabListener(this);
            actionBar.addTab(tab);
            tab = actionBar.newTab();
            tab.setText("北京").setTag("4");
            tab.setTabListener(this);
            actionBar.addTab(tab);tab = actionBar.newTab();
            tab.setText("军事").setTag("5");
            tab.setTabListener(this);
            actionBar.addTab(tab);

        }

        pager.setCurrentItem(position, false);
    }
    //////////////viewpager的滚动事件
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        ActionBar actionBar=getActionBar();

        if(actionBar!=null){
            //1.
        ActionBar.Tab tab = actionBar.getTabAt(position);
        actionBar.selectTab(tab);

            //2.ActionBar 可以通过 setSelectedNavigationItem(int position)
            // 进行Tab的选中
//            actionBar.setSelectedNavigationItem(position);
        }



    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    /////////////////////////////tab的点击事件
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        ActionBar actionBar=getActionBar();
        //获取选中的tab的index
        int index=actionBar.getSelectedNavigationIndex();
        //tab切换，viewpager跟着切换
        pager.setCurrentItem(index,false);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}