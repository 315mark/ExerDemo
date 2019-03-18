package zkch.com.exerdemo.cniaow.model;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import zkch.com.exerdemo.cniaow.mvp.contract.AppManagerContract;
import zkch.com.exerdemo.common.ACache;
import zkch.com.exerdemo.common.apkparest.AndroidApk;
import zkch.com.exerdemo.common.constant.Constant;
import zkch.com.exerdemo.common.utils.AppUtils;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class AppManagerModel implements AppManagerContract.IAppManagerModel {

    private Context context;
    private RxDownload mRxDownload;

    public AppManagerModel(Context context, RxDownload rxDownload) {
        this.context = context;
        this.mRxDownload = rxDownload;
    }

    //RxDownload 构造方法
    @Override
    public RxDownload getRxDownload() {
        return mRxDownload;
    }

    @Override
    public Observable<List<DownloadRecord>> getDownloadRecord() {
        return mRxDownload.getTotalDownloadRecords();
    }

    @Override
    public Observable<List<AndroidApk>> getLocalApp() {
        final String dir = ACache.get(context).getAsString(Constant.APK_DOWNLOAD_DIR);

        return Observable.create(emitter -> {
            emitter.onNext(scanApks(dir));
            emitter.onComplete();
        });
    }

    @Override
    public Observable<List<AndroidApk>> getInstalledApp() {
        return Observable.create(emitter -> {
            emitter.onNext(AppUtils.getInstalledApps(context));
            emitter.onComplete();
        });
    }

    /**
     * 扫描APK文件
     *
     * @param dir
     * @return
     */
    private List<AndroidApk> scanApks(String dir) {
        File file = new File(dir);
        if (!file.isDirectory()) {
            throw new RuntimeException("file is not Dir");
        }
        File[] apks = file.listFiles(pathname -> {
            if (file.isDirectory()) {
                return false;
            }
            return file.getName().endsWith(".apk");
        });

        List<AndroidApk> apklist = new ArrayList<>();
        for (File apk : apks) {
            AndroidApk androidApk = AndroidApk.read(context, apk.getPath());
            apklist.add(androidApk);
        }
        return apklist;
    }
}
