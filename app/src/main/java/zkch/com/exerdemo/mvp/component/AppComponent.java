package zkch.com.exerdemo.mvp.component;

import javax.inject.Singleton;

import dagger.Component;
import zkch.com.exerdemo.mvp.component.module.AppModule;


/**
 *   AppModule 是单例 所以 AppComponent 也是必须是单例
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

//    public ApiService getApiService();  调用请求的接口

}
