package com.qianfeng.ds.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

/**
 * Created by Administrator on 2015/1/22.
 */
public class DetailActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private ViewPager pager;
    private RadioGroup radioGroup;
    private int position;
    private HorizontalScrollView scrollView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //水平滑动
        scrollView = (HorizontalScrollView)findViewById(R.id.scroll_view);
        //初始化ViewPager
        pager = (ViewPager)findViewById(R.id.pager);
        //初始化RadioGroup
        radioGroup = (RadioGroup)findViewById(R.id.note_tab_bar);
        radioGroup.setOnCheckedChangeListener(this);
        //获取Mainactivity传递的position
        Intent intent=getIntent();
        position=intent.getIntExtra("position",0);
        List<String> noteList = DataStore.getInstance().getNoteList();
        NoteFragmentAdapter adapter=new NoteFragmentAdapter(getSupportFragmentManager(),noteList);
        pager.setAdapter(adapter);

        pager.setOnPageChangeListener(this);

        pager.setCurrentItem(position,false);
        if(position==0){
            // 为0，也就是ViewPager当前不会进行OnPageChangeListener的回调
            // 因为ViewPager默认显示也是0，所以不会进行处理
            // 需要手动给 RadioGroup 设置选中位置
            View view= radioGroup.getChildAt(0);
            if(view!=null){
                if(view instanceof RadioButton){
                    RadioButton btn=(RadioButton)view;
                    btn.setChecked(true);
                }
            }
        }


    }

    /**
     * Radiogroup点击事件
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // TODO 考虑如何根据 RadioGroup 来获取点击RadioButton的索引位置，
        // TODO 这样如果能够获取，ViewPager.setCurrentItem(int position) 就可以调用了
        //group里边btn的个数
        int count=group.getChildCount();
        position=-1;
        //循环来获取每一个radiobutton
        for(int i=0;i<count;i++){
            View view=group.getChildAt(i);
            if(view!=null){
                if(view instanceof RadioButton){
                    RadioButton btn=(RadioButton)view;
                    //判断是否选中
                    if(btn.isChecked()){
                        position=i;
                        break;
                    }
                }
            }
        }
        //btn切换的同时，viewpager跟着切换
        if(position>-1){
            pager.setCurrentItem(position,false);
        }

    }
///////////////////////////////////////////////////viewpager的监听事件


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //同理，viewpager切换时，btn跟着切换
        View view=radioGroup.getChildAt(position);
        if(view!=null){
            if(view instanceof RadioButton){
                RadioButton btn=(RadioButton)view;
                btn.setChecked(true);

                int x=btn.getLeft();
                int y=btn.getTop();
                scrollView.smoothScrollTo(x,y);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}