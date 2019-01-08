package zkch.com.exerdemo.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.widget.Toast;

import java.util.Stack;

/**
 * 应用Activity管理
 */

public class AppActivityManage {


    private static Stack<Activity> activityStack;
    private static AppActivityManage instance;

    private AppActivityManage() {
    }

    /**
     * 单一实例
     */
    public static AppActivityManage getInstance() {
        if (instance == null) {
            synchronized (AppActivityManage.class) {
                if (instance == null) {
                    instance = new AppActivityManage();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 将Activity从堆栈移除
     */
    public void removeActivity(Activity activity) {
        if (activityStack != null) {
            activityStack.remove(activity);
            activity = null;
        }
    }


    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity getCurrentActivity() {
        Activity activity = null;
        if (activityStack != null) {
            activity = activityStack.lastElement();
        }
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        Activity activity = activityStack.lastElement();
        if (activity != null) {
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }

    }

    /**
     * 结束除指定类名外的所有Activity
     */
    public void finishOthersActivity(Class<?> cls) {
        for (Activity activity : activityStack) {

            if (!activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }

    }


    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (activityStack != null && activityStack.size() > 0) {
            for (int i = 0, size = activityStack.size(); i < size; i++)
                if (null != activityStack.get(i))
                    activityStack.get(i).finish();
        }
        activityStack.clear();
    }


    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }


    /**
     * 退出程序
     */
    private long exitTime = 0;

    public void exitApp(Context context) {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(context, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppActivityManage.getInstance().AppExit(context);
        }
    }
}
