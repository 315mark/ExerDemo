package zkch.com.exerdemo.cniaow.mvp.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.mvp.contract.AppManagerContract;
import zkch.com.exerdemo.common.ACache;
import zkch.com.exerdemo.common.apkparest.AndroidApk;
import zkch.com.exerdemo.common.constant.Constant;
import zkch.com.exerdemo.common.rx.RxSchedulers;
import zkch.com.exerdemo.common.subscriber.ProgressObserver;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class AppManagerPresenter extends BasePresenter<AppManagerContract.IAppManagerModel, AppManagerContract.AppManagerView> {

    @Inject
    public AppManagerPresenter(AppManagerContract.IAppManagerModel mModel, AppManagerContract.AppManagerView mView) {
        super(mModel, mView);
    }

    /**
     * 获取下载中的数据
     */
    public void getDownloadingApps() {
        mModel.getDownloadRecord()
                .compose(RxSchedulers.io_mian())
                .subscribe(new ProgressObserver<List<DownloadRecord>>(mContext, mView) {
                    @Override
                    public void onNext(List<DownloadRecord> downloadRecords) {
                        mView.showDownloading(downloadRecordFilter(downloadRecords));
                    }
                });
    }


    public RxDownload getRxDownload() {
        return mModel.getRxDownload();
    }

    /**
     * 筛选数据
     */
    public List<DownloadRecord> downloadRecordFilter(List<DownloadRecord> recordList) {
        List<DownloadRecord> list = new ArrayList<>();
        for (DownloadRecord record : recordList) {
            if (record.getFlag() != DownloadFlag.COMPLETED) {
                list.add(record);
            }
        }
        return list;
    }

    /**
     * 获取需要升级的app
     */
    public void getUpdateApps() {
        String json = ACache.get(mContext).getAsString(Constant.APP_UPDATE_LIST);
        if (!TextUtils.isEmpty(json)) {
            Gson gson = new Gson();
            List<AppInfo> apps = gson.fromJson(json, new TypeToken<List<AppInfo>>() {
            }.getType());
            Observable.just(apps).compose(RxSchedulers.io_mian())
                    .subscribe(new ProgressObserver<List<AppInfo>>(mContext, mView) {
                        @Override
                        public void onNext(List<AppInfo> appInfos) {
                            mView.showUpdateApps(appInfos);
                        }
                    });
        }
    }

    /**
     * 获取已安装的数据
     */
    public void getInstalledApps() {
        mModel.getInstalledApp().compose(RxSchedulers.io_mian())
                .subscribe(new ProgressObserver<List<AndroidApk>>(mContext, mView) {
                    @Override
                    public void onNext(List<AndroidApk> androidApks) {
                        mView.showApp(androidApks);
                    }
                });
    }

    /**
     * 获取本地Apk文件
     */
    public void getLocalApks() {
        mModel.getLocalApp().compose(RxSchedulers.io_mian())
                .subscribe(new ProgressObserver<List<AndroidApk>>(mContext, mView) {
                    @Override
                    public void onNext(List<AndroidApk> androidApks) {
                        mView.showApp(androidApks);
                    }
                });
    }

}
