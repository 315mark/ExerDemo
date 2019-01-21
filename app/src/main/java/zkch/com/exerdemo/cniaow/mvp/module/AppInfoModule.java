package zkch.com.exerdemo.cniaow.mvp.module;

import dagger.Module;
import dagger.Provides;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.model.AppInfoModel;
import zkch.com.exerdemo.cniaow.mvp.contract.AppInfoContract;


@Module
public class AppInfoModule {
    private AppInfoContract.AppInfoView mView;

    public AppInfoModule(AppInfoContract.AppInfoView mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.AppInfoView provideView() {
        return mView;
    }

    @Provides
    public AppInfoModel provideModel(ApiService apiService) {
        return new AppInfoModel(apiService);
    }

}
