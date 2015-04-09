package com.qianfeng.ds.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //初始化控件
        ListView listView=(ListView)findViewById(R.id.note_list);

        List<String> data = DataStore.getInstance().getNoteList();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5");
        //填充数据
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, data));
        //设置点击事件
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,ActionBarTabActivity.class);
        //传递当前点击项的索引
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
