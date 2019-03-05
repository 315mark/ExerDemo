package zkch.com.exerdemo.cniaow.fragment;

import android.support.v7.widget.RecyclerView;

import zkch.com.exerdemo.cniaow.adapter.AndroidApkAdapter;


public class InstalledAppFragment extends AppManagerFragment {

    private AndroidApkAdapter mAdapter;

    @Override
    protected void init() {
        super.init();
        mPresenter.getInstalledApps();
    }

    @Override
    protected RecyclerView.Adapter setAdapter() {
        return mAdapter;
    }


}
