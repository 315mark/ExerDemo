package zkch.com.exerdemo;
import android.content.Context;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.view.View;


import com.mikepenz.iconics.Iconics;

import zkch.com.exerdemo.mvp.component.AppComponent;
import zkch.com.exerdemo.mvp.component.DaggerAppComponent;
import zkch.com.exerdemo.mvp.component.module.AppModule;
import zkch.com.exerdemo.typeface.AliFont;
import zkch.com.exerdemo.util.AppContextUtils;
import zkch.com.exerdemo.util.Utils;


/**
 * 应用Application  实现AppComponent
 */

public class AppApplication extends MultiDexApplication {

    private AppComponent component;
    private static AppApplication appInstance;
    private View mView;

    public View getmView() {
        return mView;
    }

    public void setmView(View mView) {
        this.mView = mView;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;

        // AppComponent  配置完 先build 在调用下面这段
        //DaggerAppComponent.creat() 方法直接创建 前提注解组建不带参数
        //
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();

        AppContextUtils.init(this);//应用工具初始化

        Utils.init(this);//初始化常用工具包

        //自定义阿里图标库
        //only required if you add a custom or generic font on your own
        Iconics.init(getApplicationContext());
        //register custom fonts like this (or also provide a font definition file)
        Iconics.registerFont(new AliFont());
    }

    /**
     * 获得Application对象
     */
    public static AppApplication getInstance() {
        return appInstance;
    }


    public AppComponent getAppComponent() {
        return component;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);//解决65536问题
    }

}
