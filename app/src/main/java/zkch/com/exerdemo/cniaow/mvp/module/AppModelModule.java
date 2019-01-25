package zkch.com.exerdemo.cniaow.mvp.module;

import dagger.Module;
import dagger.Provides;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.model.AppInfoModel;

@Module
public class AppModelModule {

    //TODO 多余的抽取操作 直接写在AppDetailModule

    @Provides
    public AppInfoModel provideModel(ApiService apiService) {
        return new AppInfoModel(apiService);
    }

}
