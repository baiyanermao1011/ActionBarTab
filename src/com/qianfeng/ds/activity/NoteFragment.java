package com.qianfeng.ds.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/1/22.
 */
public class NoteFragment extends Fragment {

    private TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_note, container, false);
        //初始化控件
        title = (TextView)ret.findViewById(R.id.txt_title);
        //先判断当前数据有无存储，有从存储中获取数据
        if(savedInstanceState!=null){
            String str=savedInstanceState.getString("title");
            title.setText(str);
        }else{
            Bundle bundle=getArguments();
            if(bundle!=null){
                title.setText(bundle.getString("note"));
            }
        }

        return ret;

    }

    /**
     * 存储当前数据
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("title",title.getText().toString());
    }
}