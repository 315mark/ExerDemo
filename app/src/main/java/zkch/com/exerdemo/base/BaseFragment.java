package zkch.com.exerdemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import zkch.com.exerdemo.AppApplication;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.presenter.BasePresenter;


/**
 * BaseFragment
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    private View rootView;
    private Unbinder bind;
    private AppApplication mApplication;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(getLayoutId(), container, false);
        bind = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mApplication = (AppApplication) getActivity().getApplication();
        setUpActivityComponent(mApplication.getAppComponent());
        init();
    }

    /**
     * protected  修饰指继承该类可执行方法
     */
    protected abstract int getLayoutId();

    protected abstract void setUpActivityComponent(AppComponent appComponent);

    protected abstract void init();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind != Unbinder.EMPTY) {
            bind.unbind();
        }
    }

}
