package zkch.com.exerdemo.cniaow.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.adapter.GuideFragmentAdapter;
import zkch.com.exerdemo.cniaow.fragment.GuideFragment;
import zkch.com.exerdemo.common.ACache;
import zkch.com.exerdemo.common.constant.Constant;
import zkch.com.exerdemo.widget.CircleIndicator;
import zkch.com.exerdemo.widget.DepthPageTransformer;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.btn_enter)
    Button btnEnter;
    @BindView(R.id.indicator)
    CircleIndicator indicator;

    GuideFragmentAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initData();
        btnEnter.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(GuideFragment.newInstance(R.drawable.guide_1, R.color.color_bg_guide1, R.string.guide_1));
        fragments.add(GuideFragment.newInstance(R.drawable.guide_2, R.color.color_bg_guide2, R.string.guide_2));
        fragments.add(GuideFragment.newInstance(R.drawable.guide_3, R.color.color_bg_guide3, R.string.guide_3));

        //适配器
        mAdapter = new GuideFragmentAdapter(getSupportFragmentManager());
        mAdapter.setFragments(fragments);

        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(mAdapter.getCount());
        viewPager.setAdapter(mAdapter);

        //viewpager和指示器联动
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        indicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                btnEnter.setVisibility(position == mAdapter.getCount() - 1 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_enter:
                ACache.get(this).put(Constant.IS_SHOW_GUIDE, "0");
                startActivity(new Intent(this, SimpleActivity.class));
                finish();
                break;
        }
    }
}
