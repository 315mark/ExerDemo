package zkch.com.exerdemo.cniaow.fragment;

import zkch.com.exerdemo.cniaow.adapter.AppInfoAdapter;
import zkch.com.exerdemo.mvp.component.AppComponent;
import zkch.com.exerdemo.mvp.presenter.AppInfoPresenter;

public class TopListFragment extends BaseAppInfoFragment {
    @Override
    int type() {
        return AppInfoPresenter.TOP_LIST;
    }

    @Override
    protected AppInfoAdapter buildAdater() {
        return null;
    }


    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
