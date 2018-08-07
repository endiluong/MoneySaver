package com.example.admin.asm_zlud.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class myFragmentAdapter extends FragmentStatePagerAdapter {
    int nOfTab;
    public final List<Fragment> mFragments=new ArrayList<>();
    public final List<String>fragTittle= new ArrayList<>();


    public myFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFrag(Fragment fragment, String tittle){
            mFragments.add(fragment);
            fragTittle.add(tittle);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position );
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragTittle.get(position);
    }
}
