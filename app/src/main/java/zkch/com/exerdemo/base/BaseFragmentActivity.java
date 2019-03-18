package zkch.com.exerdemo.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.util.JumpUtils;

import static zkch.com.exerdemo.util.BarUtils.getStatusBarHeight;

/**
 * 基类Activity 继承自FragmentActivity
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //当系统版本4.4以上可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(getLayoutResID());
        bind = ButterKnife.bind(this);
        init();
    }

    protected abstract int getLayoutResID();

    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != Unbinder.EMPTY) {
            bind.unbind();//解除绑定
        }

    }

    /**
     * 启动目标Activity
     */
    public void launch(Class<?> target) {
        JumpUtils.launch(this, target);
    }

    /**
     * 启动目标Activity, 传入数据
     */
    public void open(Bundle bundle, Class<?> target) {
        JumpUtils.launch(this, bundle, target);
    }

    /**
     * 启动目标Activity，并关闭自身
     */
    public void FinishSelf(Class<?> target) {
        JumpUtils.launchWithFinishSelf(this, target);
    }

    /**
     * 设置沉浸式状态栏
     */
    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final ViewGroup linear_bar = findViewById(R.id.bar_layout);
            final int statusHeight = getStatusBarHeight();
            linear_bar.post(() -> {
                int titleHeight = linear_bar.getHeight();
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linear_bar.getLayoutParams();
                params.height = statusHeight + titleHeight;
                linear_bar.setLayoutParams(params);
            });
        }
    }

}
