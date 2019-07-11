package zkch.com.exerdemo.cniaow.fragment;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import zkch.com.exerdemo.cniaow.adapter.DownLoadingAdapter;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class DownloadingFragment extends AppManagerFragment {

    DownLoadingAdapter mAdapter;

    @Override
    protected void init() {
        super.init();
        mPresenter.getDownloadingApps();
    }

    @Override
    protected RecyclerView.Adapter setAdapter() {
        mAdapter = new DownLoadingAdapter(mPresenter.getRxDownload());
        return mAdapter;
    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {
        mAdapter.addData(downloadRecords);
    }
}
