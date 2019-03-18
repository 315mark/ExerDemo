package zkch.com.exerdemo.cniaow.fragment;

import android.annotation.SuppressLint;

import zkch.com.exerdemo.cniaow.adapter.AppInfoAdapter;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.component.DaggerAppInfoComponent;
import zkch.com.exerdemo.cniaow.mvp.module.AppInfoModule;

@SuppressLint("ValidFragment")
public class CategoryAppFragment extends BaseAppInfoFragment {
    private int categoryId;
    private int mFlagType;

    public CategoryAppFragment(int categoryId, int mFlagType) {
        this.categoryId = categoryId;
        this.mFlagType = mFlagType;
    }

    @Override
    protected void init() {
        mPresenter.requestCategoryApps(categoryId, page, mFlagType);
        initRecyView();
    }

    @Override
    int type() {
        return 0;
    }

    @Override
    public AppInfoAdapter buildAdater() {
        return AppInfoAdapter.builder().showPosition(false)
                .showBrief(true).showCategoryName(false)
                .rxDownload(mRxDownload).build();

    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build().injectCategoryAppFragment(this);

    }
}
