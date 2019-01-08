package zkch.com.exerdemo.cniaow;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.base.BaseActivity;
import zkch.com.exerdemo.mvp.component.AppComponent;

public class SimpleActivity extends BaseActivity {


    @BindView(R.id.Toolbar)
    android.support.v7.widget.Toolbar Toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.navigaview_left)
    NavigationView navigaviewLeft;
    @BindView(R.id.drawer_Layout)
    DrawerLayout drawerLayout;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_simple;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }


}
