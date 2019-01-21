package zkch.com.exerdemo.cniaow.mvp.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 单例实现 new Gson 等实例
 * Application 级别module  级别高于普通的module
 */
@Module
public class AppModule {

    private Application mApp;

    public AppModule(Application mApplication) {
        this.mApp = mApplication;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return mApp;
    }

    @Provides    //提供
    @Singleton  //单例
    public Gson provideGson() {
        return new Gson();
    }
}
