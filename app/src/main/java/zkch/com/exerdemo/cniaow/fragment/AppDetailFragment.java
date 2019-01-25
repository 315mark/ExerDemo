package zkch.com.exerdemo.cniaow.fragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;

import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.contract.AppInfoContract;
import zkch.com.exerdemo.cniaow.mvp.presenter.AppDetailPresenter;

@SuppressLint("ValidFragment")
public class AppDetailFragment extends ProgressFragment<AppDetailPresenter> implements AppInfoContract.AppDetailView {

    private LayoutInflater mInflater;
    private int mAppId;

    public AppDetailFragment(int mAppId) {
        this.mAppId = mAppId;
    }

    //AE56SG73dv
    @Override
    protected void init() {
        mInflater = LayoutInflater.from(getActivity());
        mPresenter.getAppDetail(mAppId);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_app_detail;
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {

    }

    @Override
    public void showAppDetail(AppInfo appInfo) {

    }
}
