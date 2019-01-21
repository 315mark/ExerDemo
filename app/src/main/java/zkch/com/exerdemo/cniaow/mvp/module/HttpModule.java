package zkch.com.exerdemo.cniaow.mvp.module;

import android.app.Application;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zkch.com.exerdemo.BuildConfig;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.common.http.CommonParamsIntercepter;

/**
 * 封装单例模式OKHTTP网络请求
 */
@Module
public class HttpModule {

    @Provides
    @Singleton
    public OkHttpClient getOkHttpClient(Gson gson, Application application) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            //日志拦截器
            HttpLoggingInterceptor logInter = new HttpLoggingInterceptor();
            //设置记录整个body
            logInter.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logInter);
        }

        //网络拦截器
        return builder.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new CommonParamsIntercepter(application, gson))
                .build();
    }

    @Provides
    @Singleton
    public Retrofit getRetrofit(OkHttpClient okHttp) {
        return new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttp)
                .build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }


}
