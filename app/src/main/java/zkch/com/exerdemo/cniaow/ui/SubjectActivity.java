package zkch.com.exerdemo.cniaow.ui;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.base.BaseActivity;
import zkch.com.exerdemo.cniaow.bean.Subject;
import zkch.com.exerdemo.cniaow.fragment.SubjectDetailFragment;
import zkch.com.exerdemo.cniaow.fragment.SubjectFragment;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.common.rx.RxBus;

//热门主题
public class SubjectActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar Toolbar;

    private FragmentManager fragmentManager;

    public static final int FRAGMENT_SUBJECT = 0;
    public static final int FRAGMENT_DETAIL = 1;

    private int fragmentIndex = FRAGMENT_SUBJECT;

    private SubjectFragment mFragment;
    private SubjectDetailFragment mDetailFragment;

    @Override
    protected int getLayoutResID() {
        return R.layout.template_toolbar_framelayout;
    }

    @Override
    protected void init() {
        initToolbar();

        fragmentManager = getSupportFragmentManager();
        showSubjectsFragment();
        showSubjectDetailFragmentRxBus();
    }

    /**
     * 监听切换SubjectDetailFragment
     */
    @SuppressLint("CheckResult")
    private void showSubjectDetailFragmentRxBus() {
        RxBus.getInstance().toObservable(Subject.class)
                .subscribe(this::showSubjectDetailFragment);
    }

    /**
     * 显示主题列表页面
     */
    private void showSubjectsFragment() {
        fragmentIndex = FRAGMENT_SUBJECT;
        Toolbar.setTitle("热门主题");
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏的Fragment
        hideFragment(ft);
        if (mFragment == null) {
            mFragment = new SubjectFragment();
            ft.add(R.id.content, mFragment);
        } else {
            ft.show(mFragment);
        }
        ft.commit();
    }

    private void showSubjectDetailFragment(Subject subject) {
        fragmentIndex = FRAGMENT_DETAIL;
        Toolbar.setTitle(subject.getTitle());
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mDetailFragment != null) {
            transaction.remove(mDetailFragment);
        }
        mDetailFragment = new SubjectDetailFragment(subject);
        transaction.add(R.id.content, mDetailFragment);
        transaction.commit();

    }

    private void hideFragment(FragmentTransaction ft) {
        if (mFragment != null) {
            ft.hide(mFragment);
        }
        if (mDetailFragment != null) {
            ft.hide(mDetailFragment);
        }
    }

    private void initToolbar() {
        Toolbar.setNavigationIcon(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_arrow_back).sizeDp(16)
                .color(ContextCompat.getColor(this, R.color.white))
        );
        Toolbar.setNavigationOnClickListener(v -> {
            if (fragmentIndex == FRAGMENT_SUBJECT) {
                finish();
            } else {
                showSubjectsFragment();
            }
        });
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
