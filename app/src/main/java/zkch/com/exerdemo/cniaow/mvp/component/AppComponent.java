package zkch.com.exerdemo.cniaow.mvp.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.mvp.module.AppModule;
import zkch.com.exerdemo.cniaow.mvp.module.DownloadModule;
import zkch.com.exerdemo.cniaow.mvp.module.HttpModule;
import zkch.com.exerdemo.common.rx.RxErrorHandler;
import zlc.season.rxdownload2.RxDownload;

/**
 * AppModule 是单例 所以 AppComponent 也是必须是单例
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class, DownloadModule.class})
public interface AppComponent {
    //调用请求的接口
    ApiService getApiService();

    public Application getApplication();

    public RxErrorHandler getRxErrorHandler();

    public RxDownload getRxDownload();
}
