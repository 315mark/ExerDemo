package zkch.com.exerdemo.cniaow.fragment;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import zkch.com.exerdemo.cniaow.adapter.AndroidApkAdapter;
import zkch.com.exerdemo.common.apkparest.AndroidApk;


public class InstalledAppFragment extends AppManagerFragment {

    private AndroidApkAdapter mAdapter;

    @Override
    protected void init() {
        super.init();
        mPresenter.getInstalledApps();
    }

    @Override
    protected RecyclerView.Adapter setAdapter() {
        mAdapter = new AndroidApkAdapter(AndroidApkAdapter.FLAG_APP);
        return mAdapter;
    }

    @Override
    public void showApp(List<AndroidApk> apps) {
        mAdapter.addData(apps);
    }
}
