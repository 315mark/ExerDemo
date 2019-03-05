package zkch.com.exerdemo.cniaow.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.base.BaseActivity;
import zkch.com.exerdemo.cniaow.adapter.ViewPagerAdapter;
import zkch.com.exerdemo.cniaow.bean.FragmentInfo;
import zkch.com.exerdemo.cniaow.fragment.DownloadedFragment;
import zkch.com.exerdemo.cniaow.fragment.DownloadingFragment;
import zkch.com.exerdemo.cniaow.fragment.InstalledAppFragment;
import zkch.com.exerdemo.cniaow.fragment.UpgradeAppFragment;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;

public class AppManagerActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar Toolbar;
    @BindView(R.id.tabs)
    TabLayout Tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_app_manager;
    }

    @Override
    protected void init() {
        initToolbar();

        initTabLayout();
    }

    private void initToolbar() {
        Toolbar.setNavigationIcon(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_arrow_back).sizeDp(16)
                .color(ContextCompat.getColor(this, R.color.white))
        );
        Toolbar.setNavigationOnClickListener(v -> finish());
        Toolbar.setTitle("下载管理");
    }

    private void initTabLayout() {
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), initFragment());
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
        Tabs.setupWithViewPager(viewPager);
    }

    private List<FragmentInfo> initFragment() {
        List<FragmentInfo> mFragments = new ArrayList<>();
        mFragments.add(new FragmentInfo("下载", DownloadingFragment.class));
        mFragments.add(new FragmentInfo("已完成", DownloadedFragment.class));
        mFragments.add(new FragmentInfo("升级", UpgradeAppFragment.class));
        mFragments.add(new FragmentInfo("已安装", InstalledAppFragment.class));
        return mFragments;
    }

    /**
     * 设置Activity Component
     *
     * @param appComponent
     */
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

}
