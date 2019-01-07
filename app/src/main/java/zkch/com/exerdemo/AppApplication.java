package zkch.com.exerdemo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;


import com.mikepenz.iconics.Iconics;

import zkch.com.exerdemo.base.AppActivityManage;
import zkch.com.exerdemo.typeface.AliFont;
import zkch.com.exerdemo.util.AppContextUtils;
import zkch.com.exerdemo.util.Utils;


/**
 * 应用Application
 * Created by chenweisong on 2017/9/6.
 */

public class AppApplication extends MultiDexApplication {

    private static AppApplication appInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        AppContextUtils.init(this);//应用工具初始化

        Utils.init(this);//初始化常用工具包
        //Bugly初始化
        //第三个参数为SDK调试模式开关，调试模式的行为特性如下：
        // 输出详细的Bugly SDK的Log；
        // 每一条Crash都会被立即上报；
        // 自定义日志将会在Logcat中输出。
        // 建议在测试阶段建议设置成true，发布时设置为false。
//        CrashReport.initCrashReport(getApplicationContext(), "6c4764d087", false);


        activityStackManager();//Activity堆栈管理

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

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);//解决65536问题
    }


    /**
     * Activity堆栈管理
     */
    private void activityStackManager() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                //添加Activity到Activity堆栈管理列表中
                AppActivityManage.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                //将Activity从堆栈管理中移除
                AppActivityManage.getInstance().removeActivity(activity);
            }
        });
    }


}
