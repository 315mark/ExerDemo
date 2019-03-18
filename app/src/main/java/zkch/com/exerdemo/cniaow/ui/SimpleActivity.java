package zkch.com.exerdemo.cniaow.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hwangjr.rxbus.RxBus;
import com.jakewharton.rxbinding2.view.RxView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.base.BaseActivity;
import zkch.com.exerdemo.cniaow.adapter.ViewPagerAdapter;
import zkch.com.exerdemo.cniaow.bean.FragmentInfo;
import zkch.com.exerdemo.cniaow.bean.User;
import zkch.com.exerdemo.cniaow.fragment.CategoryFragment;
import zkch.com.exerdemo.cniaow.fragment.GamesFragment;
import zkch.com.exerdemo.cniaow.fragment.RecommendFragment;
import zkch.com.exerdemo.cniaow.fragment.TopListFragment;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.component.DaggerMainComponent;
import zkch.com.exerdemo.cniaow.mvp.contract.MainContract;
import zkch.com.exerdemo.cniaow.mvp.module.MainModule;
import zkch.com.exerdemo.cniaow.mvp.presenter.SimplePresenter;
import zkch.com.exerdemo.common.ACache;
import zkch.com.exerdemo.common.constant.Constant;
import zkch.com.exerdemo.typeface.AliFont;
import zkch.com.exerdemo.util.PermissionUtils;
import zkch.com.exerdemo.util.ToastUtils;
import zkch.com.exerdemo.widget.BadgeActionProvider;

public class SimpleActivity extends BaseActivity<SimplePresenter> implements MainContract.MainView {


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.drawer_Layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.Toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigaview_left)
    NavigationView navigaviewLeft;

    private ImageView mUserHeadView;
    private TextView mTextUserName;
    private View headerView;

    private BadgeActionProvider badgeActionProvider;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_simple;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void init() {
        RxBus.get().register(this);
        //此方法可抽取到Presenter层
        PermissionUtils.requestPermisson(this, Manifest.permission.READ_PHONE_STATE)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        initDrawerLayout();
                        initTLabayout();
                        initToolbar();
                        initUser();
                    } else {
                        ToastUtils.showShort("请设置权限");
                    }
                });
//        mPresenter.requestPermisson();
        mPresenter.getAppUpdateInfo();
    }


    private void initUser() {
        Object user = ACache.get(this).getAsObject(Constant.USER);
        // TODO: 判断是否存储，不存储则不跳转 subscribe( new Consumer<Object>())
        if (user == null) {
            addDisposable(RxView.clicks(headerView)
                    .throttleFirst(2, TimeUnit.SECONDS) //防重复点击
                    .subscribe(o -> startActivity(new Intent(this, LoginActivity.class)))
            );
        } else {

            User mUser = (User) user;
            initUserHeadView(mUser);
        }

    }

    /**
     * 退出登录
     */
    private void logout() {
        ACache aCache = ACache.get(this);
        aCache.put(Constant.TOKEN, "");
        aCache.put(Constant.USER, "");

        mUserHeadView.setImageDrawable(new IconicsDrawable(this, AliFont.Icon.cniao_head).colorRes(R.color.white));
        mTextUserName.setText("未登录");
        headerView.setOnClickListener(v -> {
            startActivity(new Intent(SimpleActivity.this, LoginActivity.class));
        });

    }

    //设置头像
    private void initUserHeadView(User user) {
        //Glide对网络请求的图片进行处理
        Glide.with(this).load(user.getLogo_url()).apply(RequestOptions.circleCropTransform()).into(mUserHeadView);

        mTextUserName.setText(user.getUsername());
    }

    /**
     * 初始化抽屉布局   设置侧滑菜单的头像  用户名  ToolBar 图标
     */
    private void initDrawerLayout() {

        headerView = navigaviewLeft.getHeaderView(0);
        mUserHeadView = headerView.findViewById(R.id.img_avatar);
        mUserHeadView.setImageDrawable(new IconicsDrawable(this, AliFont.Icon.cniao_head).colorRes(R.color.white));
        mTextUserName = headerView.findViewById(R.id.user_name);
        navigaviewLeft.getMenu().findItem(R.id.item_update).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_loop));
        navigaviewLeft.getMenu().findItem(R.id.item_download).setIcon(new IconicsDrawable(this, AliFont.Icon.cniao_download));
        navigaviewLeft.getMenu().findItem(R.id.item_remove).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_trash_outline));
        navigaviewLeft.getMenu().findItem(R.id.item_setting).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_gear_outline));
        navigaviewLeft.getMenu().findItem(R.id.item_login).setIcon(new IconicsDrawable(this, AliFont.Icon.cniao_shutdown));

        //侧滑菜单的点击操作
        navigaviewLeft.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_login:
                    logout();
                    break;
                case R.id.item_update:

                    break;
                case R.id.item_download:

                    break;
                case R.id.item_remove:

                    break;
                case R.id.item_setting:

                    break;

                default:
                    break;
            }
            return false;
        });
    }

    /**
     * 初始化TavLayout  填充ToolBar
     * ActionBarDrawerToggle TooolBar+ DrawerLayout 实现侧滑按钮
     */
    private void initTLabayout() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), initFragment());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private List<FragmentInfo> initFragment() {
        List<FragmentInfo> mFragments = new ArrayList<>();
        mFragments.add(new FragmentInfo("推荐", RecommendFragment.class));
        mFragments.add(new FragmentInfo("排行", TopListFragment.class));
        mFragments.add(new FragmentInfo("游戏", GamesFragment.class));
        mFragments.add(new FragmentInfo("分类", CategoryFragment.class));
        return mFragments;
    }

    private void initToolbar() {
        mToolbar.inflateMenu(R.menu.menu_toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.open, R.string.close);
        //同步状态
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        MenuItem downloadItem = mToolbar.getMenu().findItem(R.id.action_download);
        MenuItem searchItem = mToolbar.getMenu().findItem(R.id.action_search);
        badgeActionProvider = (BadgeActionProvider) MenuItemCompat.getActionProvider(downloadItem);
        badgeActionProvider.setIcon(DrawableCompat.wrap(new IconicsDrawable(this, AliFont.Icon.cniao_download).color(ContextCompat.getColor(this, R.color.white))));
        badgeActionProvider.setOnClickListener(v -> toAPPManagerActivity(badgeActionProvider.getBadgeNum() > 0 ? 2 : 0));
        searchItem.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_search) {
                startActivity(new Intent(SimpleActivity.this, SearchActivity.class));
            }
            return false;
        });
    }

    private void toAPPManagerActivity(int position) {
        Intent intent = new Intent(SimpleActivity.this, AppManagerActivity.class);
        intent.putExtra(Constant.POSITION, position);
        startActivity(new Intent(intent));
    }


    /**
     * 注入  DaggerLoginComponent
     *
     * @param appComponent
     */
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent)
                .mainModule(new MainModule(this)).build().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    public void requestPermisSuccess() {
        //权限请求成功 再此处执行init操作
    }

    @Override
    public void requestPermisFail() {

    }

    @Override
    public void changeAppUpdateCount(int count) {
        if (count > 0) {
            badgeActionProvider.setText(count + "");
        } else {
            badgeActionProvider.hideBadge();  //隐藏
        }
    }
}
