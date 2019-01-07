package zkch.com.exerdemo.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;


/**
 * Utils初始化相关
 * <p>
 * Created by chenweisong on 2017/10/10.
 */

public class AppContextUtils {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private AppContextUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        AppContextUtils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("应该先在Application中调用init(context)方法");
    }

}
