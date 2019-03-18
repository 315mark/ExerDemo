package zkch.com.exerdemo.cniaow.mvp.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import zkch.com.exerdemo.cniaow.model.AppManagerModel;
import zkch.com.exerdemo.cniaow.mvp.contract.AppManagerContract;
import zlc.season.rxdownload2.RxDownload;

@Module
public class AppManagerModule {

    private AppManagerContract.AppManagerView mView;

    public AppManagerModule(AppManagerContract.AppManagerView mView) {
        this.mView = mView;
    }


    @Provides
    public AppManagerContract.AppManagerView providerView() {
        return mView;
    }


    @Provides
    public AppManagerContract.IAppManagerModel providerModel(Application application, RxDownload rxDownload) {
        return new AppManagerModel(application, rxDownload);
    }
}
