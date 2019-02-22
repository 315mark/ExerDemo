package zkch.com.exerdemo.cniaow.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Activity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.bean.AppsUpdateBean;
import zkch.com.exerdemo.cniaow.mvp.contract.MainContract;
import zkch.com.exerdemo.common.ACache;
import zkch.com.exerdemo.common.apkparest.AndroidApk;
import zkch.com.exerdemo.common.constant.Constant;
import zkch.com.exerdemo.common.http.JsonUtil;
import zkch.com.exerdemo.common.rx.RxHttpReponseCompat;
import zkch.com.exerdemo.common.subscriber.ProgressObserver;
import zkch.com.exerdemo.util.AppUtils;
import zkch.com.exerdemo.util.PermissionUtils;

import static android.Manifest.permission.READ_PHONE_STATE;

public class SimplePresenter extends BasePresenter<MainContract.IMainModel, MainContract.MainView> {

    @Inject
    public SimplePresenter(MainContract.IMainModel mModel, MainContract.MainView mView) {
        super(mModel, mView);
    }

    @SuppressLint("CheckResult")
    public void requestPermisson() {
        PermissionUtils.requestPermisson((Activity) mContext, READ_PHONE_STATE)
                .doOnNext(aBoolean -> {
                    if (!aBoolean) {
                        mView.requestPermisFail();
                    }
                })
                .subscribe(aBoolean -> mView.requestPermisSuccess());
    }

    public void getAppUpdateInfo() {
        getInstalledAppInfo().flatMap((Function<AppsUpdateBean, ObservableSource<List<AppInfo>>>) appsUpdateBean ->
                mModel.getUpdateApps(appsUpdateBean).compose(RxHttpReponseCompat.compatResult()))
                .subscribe(new ProgressObserver<List<AppInfo>>(mContext, mView) {
                    @Override
                    public void onNext(List<AppInfo> appInfos) {
                        if (appInfos != null) {
                            ACache.get(mContext).put(Constant.APP_UPDATE_LIST, JsonUtil.toJson(appInfos));
                        }
                        mView.changeAppUpdateCount(appInfos == null ? 0 : appInfos.size());
                    }
                });
    }


    /**
     * 获取已安装的app，并进行封装
     *
     * @return
     */
    private Observable<AppsUpdateBean> getInstalledAppInfo() {
        return Observable.create(emitter -> {
            emitter.onNext(buildParams(AppUtils.getInstalledApps(mContext)));
            emitter.onComplete();
        });

    }

    /**
     * 将参数封装起来
     *
     * @param apks
     * @return
     */
    private AppsUpdateBean buildParams(List<AndroidApk> apks) {
        StringBuilder packageNameBuilder = new StringBuilder();
        StringBuilder versionCodeBuilder = new StringBuilder();

        for (AndroidApk apk : apks) {
            if (!apk.isSystem()) {
                packageNameBuilder.append(apk.getPackageName()).append(",");
                versionCodeBuilder.append(apk.getAppVersionCode()).append(",");
            }
        }

        AppsUpdateBean param = new AppsUpdateBean();
        param.setPackageName(packageNameBuilder.toString());
        param.setVersionCode(versionCodeBuilder.toString());
        return param;
    }


}
