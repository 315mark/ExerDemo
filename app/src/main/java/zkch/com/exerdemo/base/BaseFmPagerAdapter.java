package zkch.com.exerdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import java.util.List;

public class BaseFmPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments;

    public BaseFmPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Bundle Argbundle=fragments.get(position).getArguments();
        if (Argbundle !=null && TextUtils.isEmpty(Argbundle.getString("title"))){
            return Argbundle.getString("title");
        }else{
            return super.getPageTitle(position);
        }

    }
}
