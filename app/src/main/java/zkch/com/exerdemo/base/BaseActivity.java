package zkch.com.exerdemo.base;

import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.mikepenz.iconics.context.IconicsLayoutInflater;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import zkch.com.exerdemo.AppApplication;
import zkch.com.exerdemo.mvp.component.AppComponent;
import zkch.com.exerdemo.presenter.BasePresenter;
import zkch.com.exerdemo.util.JumpUtils;
import zkch.com.exerdemo.util.KeyboardUtils;


/**
 * 基础Activity
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected AppApplication mApp;
    private Unbinder bind;

    @Inject
    T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(),  new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//防止输入法键盘遮挡输入框

        setContentView(getLayoutResID());
        bind = ButterKnife.bind(this);
        mApp= (AppApplication) getApplication();
        //组件依赖注入全局级别的Application component
        setupActivityComponent(mApp.getAppComponent());
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
     * 点击空白区域 自动隐藏软键盘
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            return KeyboardUtils.hiddenKeyboard(this);
        }
        return super.onTouchEvent(event);
    }

    /**
     * 设置Activity Component
     *
     * @param appComponent
     */
    protected abstract void setupActivityComponent(AppComponent appComponent);


    /**
     * 启动目标Activity
     */
    public void launch(Class<?> target) {
        JumpUtils.launch(this, target);
    }

    /**
     * 启动目标Activity, 传入数据
     */
    public void launch(Bundle bundle, Class<?> target) {
        JumpUtils.launch(this, bundle, target);
    }

    /**
     * 启动目标Activity，并关闭自身
     */
    public void launchWithFinishSelf(Class<?> target) {
        JumpUtils.launchWithFinishSelf(this, target);
    }


    /**
     * 启动目标Activity，传入数据并关闭自身
     */
    public void launchWithFinishSelf(Bundle bundle, Class<?> target) {
        JumpUtils.launchWithFinishSelf(this, bundle, target);
    }

    /**
     * 启动目标Activity，关闭时需返回结果
     **/
    public void launchNeedResult(Bundle bundle, Class<?> target, int requestCode) {
        JumpUtils.launchNeedResult(this, bundle, target, requestCode);
    }

    /**
     * 关闭当前界面，回传结果
     **/
    public void finishWithResult(Bundle bundle, Class<?> target, int resultCode) {
        JumpUtils.finishWithResult(this, bundle, target, resultCode);
    }
}
