package zkch.com.exerdemo.cniaow.fragment;

import zkch.com.exerdemo.cniaow.adapter.AppInfoAdapter;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.component.DaggerAppInfoComponent;
import zkch.com.exerdemo.cniaow.mvp.module.AppInfoModule;
import zkch.com.exerdemo.cniaow.mvp.presenter.AppInfoPresenter;

public class TopListFragment extends BaseAppInfoFragment {
    @Override
    int type() {
        return AppInfoPresenter.TOP_LIST;
    }

    @Override
    protected AppInfoAdapter buildAdater() {
        return AppInfoAdapter.builder().showPosition(true).showBrief(false)
                .showCategoryName(true).build();
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        //TODO
        DaggerAppInfoComponent.builder().appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this)).build()
                .injectTopListFragment(this);
    }


}
