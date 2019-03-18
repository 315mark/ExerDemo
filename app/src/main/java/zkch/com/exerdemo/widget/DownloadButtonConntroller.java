package zkch.com.exerdemo.widget;

import android.annotation.SuppressLint;
import android.content.Context;

import com.jakewharton.rxbinding2.view.RxView;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.bean.AppDownloadInfo;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.common.ACache;
import zkch.com.exerdemo.common.constant.Constant;
import zkch.com.exerdemo.common.rx.RxHttpReponseCompat;
import zkch.com.exerdemo.common.rx.RxSchedulers;
import zkch.com.exerdemo.common.utils.AppUtils;
import zkch.com.exerdemo.common.utils.PackageUtils;
import zkch.com.exerdemo.util.PermissionUtils;
import zkch.com.exerdemo.util.ToastUtils;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadBean;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

@SuppressLint("CheckResult")
public class DownloadButtonConntroller {

    @SuppressLint("StaticFieldLeak")
    private static RxDownload mRxDownload;

    private ApiService api;

    public DownloadButtonConntroller(RxDownload download) {
        mRxDownload = download;
        if (mRxDownload != null) {
            api = mRxDownload.getRetrofit().create(ApiService.class);
        }
    }

    public void handClick(final DownloadProgressButton btn, DownloadRecord record) {
        AppInfo info = downloadRecor2AppInfo(record);
        receiveDownloadStatus(record.getUrl()).subscribe(new DownloadConsumer(btn, info));
    }

    public void handClick(final DownloadProgressButton btn, final AppInfo appInfo) {
        if (api == null) {
            return;
        }
        isAppInstalled(btn.getContext(), appInfo)
                .flatMap((Function<DownloadEvent, ObservableSource<DownloadEvent>>)
                        downloadEvent -> {
                            if (DownloadFlag.UN_INSTALL == downloadEvent.getFlag()) {
                                return isApkFileExsit(btn.getContext(), appInfo);
                            }
                            return Observable.just(downloadEvent);
                        })
                .flatMap((Function<DownloadEvent, ObservableSource<DownloadEvent>>)
                        downloadEvent -> {
                            if (DownloadFlag.FILE_EXIST == downloadEvent.getFlag()) {
                                return getAppDownloadInfo(appInfo)
                                        .flatMap((Function<AppDownloadInfo, ObservableSource<DownloadEvent>>)
                                                appDownloadInfo -> {
                                                    appInfo.setAppDownloadInfo(appDownloadInfo);
                                                    return receiveDownloadStatus(appDownloadInfo.getDownloadUrl());
                                                });
                            }
                            return Observable.just(downloadEvent);
                        })
                .compose(RxSchedulers.io_mian())
                .subscribe(new DownloadConsumer(btn, appInfo));
    }


    private DownloadBean appInfo2DownloadBean(AppInfo info) {
        DownloadBean bean = new DownloadBean();
        bean.setUrl(info.getAppDownloadInfo().getDownloadUrl());
        bean.setSaveName(info.getReleaseKeyHash() + ".apk");
        bean.setExtra1(info.getId() + "");
        bean.setExtra2(info.getIcon());
        bean.setExtra3(info.getDisplayName());
        bean.setExtra4(info.getPackageName());
        bean.setExtra5(info.getReleaseKeyHash());
        return bean;
    }

    /**
     * downloadRecord转化为AppInfo为了AppManager中显示
     */
    public AppInfo downloadRecor2AppInfo(DownloadRecord record) {
        AppInfo info = new AppInfo();
        info.setId(Integer.parseInt(record.getExtra1()));
        info.setIcon(record.getExtra2());
        info.setDisplayName(record.getExtra3());
        info.setPackageName(record.getExtra4());
        info.setReleaseKeyHash(record.getExtra5());

        AppDownloadInfo downloadInfo = new AppDownloadInfo();
        downloadInfo.setDowanloadUrl(record.getUrl());
        info.setAppDownloadInfo(downloadInfo);

        return info;
    }

    /**
     * 判断应用是否安装
     */
    private static Observable<DownloadEvent> isAppInstalled(Context context, AppInfo appInfo) {
        DownloadEvent event = new DownloadEvent();
        event.setFlag(AppUtils.isInstalled(context, appInfo.getPackageName()) ? DownloadFlag.INSTALLED : DownloadFlag.UN_INSTALL);
        return Observable.just(event);
    }

    /**
     * 判断是否存在文件
     */
    private static Observable<DownloadEvent> isApkFileExsit(Context context, AppInfo appInfo) {
        String path = ACache.get(context).getAsString(Constant.APK_DOWNLOAD_DIR) + File.separator + appInfo.getReleaseKeyHash();
        File file = new File(path);
        DownloadEvent event = new DownloadEvent();
        event.setFlag(file.exists() ? DownloadFlag.FILE_EXIST : DownloadFlag.NORMAL);
        return Observable.just(event);
    }

    /**
     * 获取App信息
     */
    private Observable<AppDownloadInfo> getAppDownloadInfo(AppInfo appInfo) {
        return api.getAppDownloadInfo(appInfo.getId()).compose(RxHttpReponseCompat.compatResult());
    }

    /**
     * 接收下载状态
     */
    private Observable<DownloadEvent> receiveDownloadStatus(String url) {
        return mRxDownload.receiveDownloadStatus(url);
    }

    /**
     * 暂停下载
     */
    private void pausedDownload(String downloadUrl) {
        mRxDownload.pauseServiceDownload(downloadUrl).subscribe();
    }

    /**
     * 打开App
     */
    private void runApp(Context context, String packageName) {
        AppUtils.runApp(context, packageName);
    }

    /**
     * 安装App
     */
    private void installApp(Context context, AppInfo mAppInfo) {
        String path = ACache.get(context).getAsString(Constant.APK_DOWNLOAD_DIR) + File.separator + mAppInfo.getReleaseKeyHash();
        PackageUtils.install(context, path);
    }

    class DownloadConsumer implements Consumer<DownloadEvent> {
        DownloadProgressButton btn;
        AppInfo appInfo;

        private DownloadConsumer(DownloadProgressButton btn, AppInfo mAppInfo) {
            this.btn = btn;
            this.appInfo = mAppInfo;
        }

        @Override
        public void accept(DownloadEvent downloadEvent) {
            int flag = downloadEvent.getFlag();
            btn.setTag(R.id.tag_apk_flag, flag);
            //设置点击事件
            bindClick(btn, appInfo);
            switch (flag) {
                case DownloadFlag.INSTALLED:
                    btn.setText("运行");
                    break;
                case DownloadFlag.NORMAL:
                    btn.download();
                    break;
                case DownloadFlag.PAUSED:
                    btn.paused();
                    break;
                case DownloadFlag.COMPLETED:
                    btn.setText("安装");
                    break;
                case DownloadFlag.FAILED:
                    btn.setText("失败");
                    break;
                case DownloadFlag.DELETED:
                    break;
            }
        }
    }

    /**
     * 按钮点击事件
     */

    private void bindClick(DownloadProgressButton btn, AppInfo appInfo) {
        RxView.clicks(btn).subscribe(o -> {
            int flag = (int) btn.getTag(R.id.tag_apk_flag);

            switch (flag) {
                case DownloadFlag.INSTALLED: //下载完成点击安装
                    runApp(btn.getContext(), appInfo.getPackageName());
                    break;
                case DownloadFlag.STARTED:  //开始下载
                    pausedDownload(appInfo.getAppDownloadInfo().getDownloadUrl());
                    break;
                case DownloadFlag.NORMAL:
                case DownloadFlag.PAUSED:
                    startDownload(btn, appInfo);
                case DownloadFlag.COMPLETED:
                    installApp(btn.getContext(), appInfo);
                    break;
            }
        });
    }

    /**
     * 开始下载
     */
    private void startDownload(final DownloadProgressButton btn, AppInfo appInfo) {
        PermissionUtils.requestPermisson(btn.getContext(), WRITE_EXTERNAL_STORAGE)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        AppDownloadInfo downloadInfo = appInfo.getAppDownloadInfo();
                        if (downloadInfo == null) {
                            getAppDownloadInfo(appInfo).subscribe(appDownloadInfo -> {
                                appInfo.setAppDownloadInfo(appDownloadInfo);
                                download(btn, appInfo);
                            });
                        } else {
                            download(btn, appInfo);
                        }
                    } else {
                        ToastUtils.showShort("无权限");
                    }
                });
    }

    private void download(DownloadProgressButton btn, AppInfo appInfo) {
        mRxDownload.serviceDownload(appInfo2DownloadBean(appInfo)).subscribe();
        mRxDownload.receiveDownloadStatus(appInfo.getAppDownloadInfo().getDownloadUrl())
                .subscribe(new DownloadConsumer(btn, appInfo));
    }

}
