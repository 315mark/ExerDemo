package zkch.com.exerdemo.cniaow.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class GuideFragmentAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragments;

    public void setFragments(List<Fragment> mFragments) {
        if (mFragments == null) {
            fragments = new ArrayList<>();
        } else {
            fragments = mFragments;
        }
    }

    public GuideFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return fragments.size();
    }
}
