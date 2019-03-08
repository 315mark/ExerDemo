package zkch.com.exerdemo.cniaow.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.base.BaseActivity;
import zkch.com.exerdemo.cniaow.adapter.CategoryAppAdapter;
import zkch.com.exerdemo.cniaow.bean.Category;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.common.constant.Constant;

public class CategoryAppActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.Toolbar)
    Toolbar Toolbar;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private Category mCategory;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_category_app;
    }

    @Override
    protected void init() {
        getData();
        initTabLayout();
    }

    private void initTabLayout() {
        Toolbar.setTitle(mCategory.getName());
        Toolbar.setNavigationIcon(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_arrow_back)
                .sizeDp(16).color(getResources().getColor(R.color.md_white_1000)));

        Toolbar.setNavigationOnClickListener(v -> finish());

        //ViewPager的Adapter
        CategoryAppAdapter adapter = new CategoryAppAdapter(getSupportFragmentManager(), mCategory.getId());
        mViewPager.setOffscreenPageLimit(adapter.getCount());
        mViewPager.setAdapter(adapter);
        //tablayout与viewpager建立关联关系
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void getData() {
        Intent intent = getIntent();
        mCategory = (Category) intent.getSerializableExtra(Constant.CATEGORY);
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
