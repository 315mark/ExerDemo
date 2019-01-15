package zkch.com.exerdemo.ui;

import android.annotation.SuppressLint;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.jakewharton.rxbinding2.view.RxView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import android.Manifest;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.bean.User;
import zkch.com.exerdemo.base.BaseActivity;
import zkch.com.exerdemo.cniaow.adapter.ViewPagerAdapter;
import zkch.com.exerdemo.common.ACache;
import zkch.com.exerdemo.common.constant.Constant;
import zkch.com.exerdemo.mvp.component.AppComponent;
import zkch.com.exerdemo.typeface.AliFont;
import zkch.com.exerdemo.util.PermissionUtils;
import zkch.com.exerdemo.util.ToastUtils;

public class SimpleActivity extends BaseActivity {


    @BindView(R.id.Toolbar)
    Toolbar Toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.navigaview_left)
    NavigationView navigaviewLeft;
    @BindView(R.id.drawer_Layout)
    DrawerLayout drawerLayout;

    /**
     *  ActionBarDrawerToggle TooolBar+ DrawerLayout 实现侧滑按钮
     */
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ImageView mUserHeadView;
    private TextView mTextUserName;
    private View headerView;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_simple;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void init() {
        RxBus.get().register(this);
        PermissionUtils.requestPermisson(this, Manifest.permission.READ_PHONE_STATE)
                .subscribe(aBoolean -> {
                    if (aBoolean){
                        initDrawerLayout();
                        initTLabayout();
                       // initUser();
                    }else{
                        ToastUtils.showShort("请设置权限");
                    }
                });
    }


    private void initUser() {
        Object user = ACache.get(this).getAsObject(Constant.USER);
        // TODO: 判断是否存储，不存储则不跳转 subscribe( new Consumer<Object>())
        if (user ==null){
            addDisposable(RxView.clicks(headerView)
            .throttleFirst(2, TimeUnit.SECONDS) //防重复点击
            .subscribe(o -> {
                //  startActivity(new Intent(this, LoginActivity.class));
            })
            );
        }else{
           User mUser=(User)user;
           initUserHeadView(mUser);

        }

    }

    private void initUserHeadView(User user) {
        //Glide.with(this).load(user.getLogo_url()).transition(new  Gl)
    }

    /**
     * 初始化抽屉布局   设置侧滑菜单的头像  用户名  ToolBar 图标
     */
    private void initDrawerLayout() {
        navigaviewLeft =findViewById(R.id.navigaview_left);
        headerView =navigaviewLeft.getHeaderView(0);
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
            switch (item.getItemId()){
                case R.id.item_login:

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

        Toolbar.inflateMenu(R.menu.menu_toolbar);
        ActionBarDrawerToggle drawerToggle =new ActionBarDrawerToggle(this,drawerLayout,Toolbar,R.string.open,R.string.close);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);


    }
    /**
     * 初始化TavLayout  填充ToolBar
     */
    private void initTLabayout() {
        Toolbar.inflateMenu(R.menu.menu_toolbar);
        actionBarDrawerToggle =new ActionBarDrawerToggle(this,drawerLayout,Toolbar,R.string.open,R.string.close );
        //同步状态
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        ViewPagerAdapter adapter= new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     *   注入  DaggerLoginComponent
     * @param appComponent
     */
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}