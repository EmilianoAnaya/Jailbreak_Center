package com.example.jailbreak_center;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class PageAdapter extends FragmentStateAdapter {
    private List<Fragment> fragments;

    public PageAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments){
        super(fragmentActivity);
        this.fragments = fragments;

    }
    @Override
    public Fragment createFragment(int position){
        return fragments.get(position);
    }

    @Override
    public int getItemCount(){
        return fragments.size();
    }
}
