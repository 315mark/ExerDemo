package zkch.com.exerdemo.cniaow.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import zkch.com.exerdemo.cniaow.bean.FragmentInfo;

/**
 * 以FragmentList 为实体类  实现添加Fragment
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<FragmentInfo> mFragments =new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragment();
    }

    private void initFragment() {
//        mFragments.add(new FragmentInfo("推荐", RecommendFragment.class));
//        mFragments.add(new FragmentInfo("排行", TopListFragment.class));
//        mFragments.add(new FragmentInfo("游戏", GamesFragment.class));
//        mFragments.add(new FragmentInfo("分类", CategoryFragment.class));
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        try {
            fragment= (Fragment) mFragments.get(position).getFragment().newInstance();
            return fragment;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
