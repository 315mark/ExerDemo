package zkch.com.exerdemo.cniaow.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import zkch.com.exerdemo.cniaow.mvp.contract.AppManagerContract;
import zkch.com.exerdemo.common.apkparest.AndroidApk;
import zkch.com.exerdemo.common.rx.RxSchedulers;
import zkch.com.exerdemo.common.subscriber.ProgressObserver;

public class AppManagerPresenter extends BasePresenter<AppManagerContract.IAppManagerModel, AppManagerContract.AppManagerView> {

    @Inject
    public AppManagerPresenter(AppManagerContract.IAppManagerModel mModel, AppManagerContract.AppManagerView mView) {
        super(mModel, mView);
    }

    /**
     * 获取下载中的数据
     */
    public void getDownloadingApps() {

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
