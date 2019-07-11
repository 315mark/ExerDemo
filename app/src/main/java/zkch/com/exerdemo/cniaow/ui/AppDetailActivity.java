package zkch.com.exerdemo.cniaow.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;
import timber.log.Timber;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.base.BaseActivity;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.fragment.AppDetailFragment;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.presenter.AppDetailPresenter;
import zkch.com.exerdemo.common.constant.Constant;
import zkch.com.exerdemo.util.DensityUtil;

public class AppDetailActivity extends BaseActivity<AppDetailPresenter> {

    @BindView(R.id.view_temp)
    View viewTemp;
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.view_content)
    FrameLayout viewContent;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.view_coordinator)
    CoordinatorLayout viewCoordinator;

    @BindView(R.id.detail_nested_scrollview)
    NestedScrollView detailNestedScrollview;

    private AppInfo mAppInfo;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_app_detail;
    }

    @Override
    protected void init() {
        mAppInfo = (AppInfo) getIntent().getSerializableExtra(Constant.APPINFO);
        Glide.with(this).load(Constant.BASE_IMG_URL + mAppInfo.getIcon()).into(imgIcon);

        txtName.setText(mAppInfo.getDisplayName());

        toolbar.setNavigationIcon(new IconicsDrawable(this).icon(Ionicons.Icon.ion_ios_arrow_back)
                .sizeDp(16).color(getResources().getColor(R.color.md_white_1000)));

        toolbar.setNavigationOnClickListener(v -> finish());
        //TODO 获取传递视图
        initView();

        detailNestedScrollview.scrollTo(0, 0);
    }

    private void initView() {

        View view = mApp.getmView();
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        //TODO 减去手机的状态栏
        params.topMargin = top - DensityUtil.getStatusBarH(this);
        params.leftMargin = left;
        params.width = view.getWidth();
        params.height = view.getHeight();

        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(params);
        viewTemp.setLayoutParams(linearParams);

        Bitmap bitmapCache = getViewImageCache(view);
        if (bitmapCache != null) {
            // setBackground不兼容 4.1一下版本 setbackgroundResources可以替代
            viewTemp.setBackground(new BitmapDrawable(getResources(), bitmapCache));
        }

        // open();
        viewTemp.setVisibility(View.GONE);
        viewCoordinator.setVisibility(View.VISIBLE);
        initFragment();

    }

    //开启动画
    private void open() {
        int screenH = DensityUtil.getScreenH(this);
        ObjectAnimator animator = ObjectAnimator.ofFloat(viewTemp, "scaleY", 1.0f, (float) screenH);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                viewContent.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                Timber.i(" 跳转动画 ");
                viewTemp.setVisibility(View.GONE);
                viewCoordinator.setVisibility(View.VISIBLE);
                initFragment();
            }
        });
        animator.setStartDelay(500);
        animator.setDuration(1000);
        animator.start();
    }

    private void initFragment() {
        Log.i("appinfo%s", mAppInfo.getId() + "");
        AppDetailFragment fragment = new AppDetailFragment(mAppInfo.getId());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.view_content, fragment);
        transaction.commitAllowingStateLoss();
    }


    //TODO 获取缓存图片
    private Bitmap getViewImageCache(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        if (bitmap == null) {
            return null;
        }
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
        view.destroyDrawingCache();
        return bitmap;
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
