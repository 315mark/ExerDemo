package zkch.com.exerdemo.cniaow.fragment;

import zkch.com.exerdemo.cniaow.adapter.AppInfoAdapter;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.component.DaggerAppInfoComponent;
import zkch.com.exerdemo.cniaow.mvp.module.AppInfoModule;
import zkch.com.exerdemo.cniaow.mvp.presenter.AppInfoPresenter;

public class GamesFragment extends BaseAppInfoFragment {

    @Override
    public int type() {
        return AppInfoPresenter.GAME;
    }

    @Override
    public AppInfoAdapter buildAdater() {
        return AppInfoAdapter.builder().showPosition(true).showBrief(false)
                .showCategoryName(true).rxDownload(mRxDownload).build();
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build().injectGameFragment(this);
    }
}
