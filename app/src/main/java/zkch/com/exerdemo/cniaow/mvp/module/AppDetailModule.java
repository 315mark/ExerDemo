package zkch.com.exerdemo.cniaow.mvp.module;

import dagger.Module;
import dagger.Provides;
import zkch.com.exerdemo.cniaow.mvp.contract.AppInfoContract;

@Module(includes = {AppModelModule.class})  //有点多余
public class AppDetailModule {
    private AppInfoContract.AppDetailView mView;

    public AppDetailModule(AppInfoContract.AppDetailView mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.AppDetailView provideView() {
        return mView;
    }


}
