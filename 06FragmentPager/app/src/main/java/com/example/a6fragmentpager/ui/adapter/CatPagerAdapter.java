package com.example.a6fragmentpager.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.a6fragmentpager.ui.fragment.*;

public class CatPagerAdapter extends FragmentPagerAdapter {
    Kit1Fragment kit1 = new Kit1Fragment("Tesla");
    Kit10Fragment kit10 = new Kit10Fragment("Edison");
    Kit11Fragment kit11 = new Kit11Fragment("Taff");

    public CatPagerAdapter(FragmentManager fm){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return kit1;
            case 1: return kit10;
            case 2: return kit11;
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0: return kit1.getName();
            case 1: return kit10.getName();
            case 2: return kit11.getName();
        }
        return " - ";
    }

    @Override
    public int getCount() {
        return 3;
    }
}
