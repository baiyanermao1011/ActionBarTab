package com.qianfeng.ds.activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/1/21
 * Email: vhly@163.com
 */
public class DataStore {
    private static DataStore ourInstance = new DataStore();

    public static DataStore getInstance() {
        return ourInstance;
    }

    private List<String> noteList;

    private DataStore() {
        noteList = new LinkedList<String>();
    }

    public List<String> getNoteList() {
        return noteList;
    }
}
