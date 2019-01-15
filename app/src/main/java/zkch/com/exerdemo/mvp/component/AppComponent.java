package zkch.com.exerdemo.mvp.component;

import javax.inject.Singleton;

import dagger.Component;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.mvp.module.AppModule;
import zkch.com.exerdemo.mvp.module.HttpModule;

/**
 *   AppModule 是单例 所以 AppComponent 也是必须是单例
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    //调用请求的接口
    ApiService getApiService();

}
