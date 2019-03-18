package zkch.com.exerdemo.cniaow.mvp.contract;

import java.util.List;

import io.reactivex.Observable;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.ui.BaseView;
import zkch.com.exerdemo.common.apkparest.AndroidApk;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class AppManagerContract {

    public interface IAppManagerModel {
        RxDownload getRxDownload();

        Observable<List<DownloadRecord>> getDownloadRecord();

        Observable<List<AndroidApk>> getLocalApp();

        Observable<List<AndroidApk>> getInstalledApp();
    }

    public interface AppManagerView extends BaseView {
        void showDownloading(List<DownloadRecord> downloadRecords);

        void showApp(List<AndroidApk> apps);

        void showUpdateApps(List<AppInfo> appInfos);
    }


}
