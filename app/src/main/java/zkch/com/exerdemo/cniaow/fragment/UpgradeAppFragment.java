package zkch.com.exerdemo.cniaow.fragment;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import zkch.com.exerdemo.cniaow.adapter.AppInfoAdapter;
import zkch.com.exerdemo.cniaow.bean.AppInfo;

public class UpgradeAppFragment extends AppManagerFragment {

    private AppInfoAdapter mAdapter;

    @Override
    protected void init() {
        super.init();
        mPresenter.getUpdateApps();
    }

    @Override
    protected RecyclerView.Adapter setAdapter() {
        mAdapter = AppInfoAdapter.builder().updateStatus(true)
                .rxDownload(mPresenter.getRxDownload()).build();
        return mAdapter;
    }

    @Override
    public void showUpdateApps(List<AppInfo> appInfos) {
        super.showUpdateApps(appInfos);
        mAdapter.addData(appInfos);
    }
}
