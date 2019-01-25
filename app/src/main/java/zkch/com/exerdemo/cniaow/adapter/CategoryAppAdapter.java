package zkch.com.exerdemo.cniaow.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import zkch.com.exerdemo.cniaow.fragment.CategoryAppFragment;

/**
 * mSavedState 保存每个Fragment的状态信息的列表
 * mFragments 保存每个Fragment实例对象的列表
 * FragmentStatePagerAdapter在instantiateItem方法中，创建新Fragment后，
 * 读取对应Fragment状态对其进行初始化设置，并且只使用到add方法。
 * FragmentStatePagerAdapter在destroyItem方法中，销毁Fragment时，保存其Fragment状态，并且使用remove方法移除Fragment。
 * FragmentStatePagerAdapter重载了saveState方法和restoreState方法，在其中对于Fragment实例列表和Fragment状态列表进行保存和读取。
 */
public class CategoryAppAdapter extends FragmentStatePagerAdapter {

    private final int categoryId;
    List<String> titles = new ArrayList<>();

    public CategoryAppAdapter(FragmentManager fm, int categoryId) {
        super(fm);
        this.categoryId = categoryId;
        //TODO set List data
        titles.add("精品");
        titles.add("排行");
        titles.add("新品");
    }

    @Override
    public Fragment getItem(int position) {
        return new CategoryAppFragment(categoryId, position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
