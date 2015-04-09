package com.qianfeng.ds.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/1/22.
 */
public class NoteFragmentAdapter extends FragmentStatePagerAdapter {

    private List<String> notes;

    public NoteFragmentAdapter(FragmentManager fm,List<String> notes) {
        super(fm);
        this.notes=notes;
    }

    @Override
    public Fragment getItem(int position) {
        String note = notes.get(position);

        Fragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putString("note", note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=notes.get(position);
        return title;
    }
}
