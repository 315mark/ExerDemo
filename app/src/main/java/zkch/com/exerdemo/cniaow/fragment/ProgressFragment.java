package zkch.com.exerdemo.cniaow.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import zkch.com.exerdemo.AppApplication;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.presenter.BasePresenter;
import zkch.com.exerdemo.cniaow.ui.BaseView;


/**
 * 打造带进度的Fragment 基类
 *
 * @param <T>
 */
public abstract class ProgressFragment<T extends BasePresenter> extends Fragment implements BaseView {

    private FrameLayout mRootView;
    private FrameLayout mContentView;
    private View mViewProgress;
    private View mViewEmpty;
    private TextView mTextError;
    private Unbinder unbinder;
    protected AppApplication mApplication;
    CompositeDisposable mCompositeDisposable;

    @Inject
    public T mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (FrameLayout) inflater.inflate(R.layout.fragment_progress, container, false);
        mContentView = mRootView.findViewById(R.id.view_content);
        mViewProgress = mRootView.findViewById(R.id.view_progress);
        mViewEmpty = mRootView.findViewById(R.id.view_empty);
        mTextError = mRootView.findViewById(R.id.text_tip);
        addDisposable(RxView.clicks(mViewEmpty).subscribe(o -> onEmptyViewClick()));

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mApplication = (AppApplication) getActivity().getApplication();
        //dagger2 注解器
        setupAcitivtyComponent(mApplication.getAppComponent());
        //添加布局
        setRealContentView();
        init();
    }

    /**
     * 这里添加最核心布局
     */
    private void setRealContentView() {
        View RealView = LayoutInflater.from(getActivity()).inflate(setLayout(), mContentView, true);
        unbinder = ButterKnife.bind(this, RealView);
    }


    public void showProgressView() {
        showView(R.id.view_progress);

    }

    public void showContentView() {

        showView(R.id.view_content);
    }

    public void showEmptyView() {

        showView(R.id.view_empty);
    }

    public void showEmptyView(String disMsg) {
        showEmptyView();
        mTextError.setText(disMsg);
    }


    /**
     * 网络请求 多布局选择操作
     *
     * @param viewId
     */
    private void showView(int viewId) {
        for (int i = 0; i < mRootView.getChildCount(); i++) {
            View mRootViewChildAt = mRootView.getChildAt(i);
            if (mRootViewChildAt.getId() == viewId) {
                mRootViewChildAt.setVisibility(View.VISIBLE);
            } else {
                mRootViewChildAt.setVisibility(View.GONE);
            }
        }
    }


    /**
     * 添加订阅
     */
    public void addDisposable(Disposable mDisposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(mDisposable);
    }

    /**
     * 取消所有订阅
     */
    public void clearDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearDisposable();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    @Override
    public void showLoading() {
        showProgressView();
    }

    @Override
    public void showError(String disMsg) {
        showEmptyView(disMsg);
    }

    @Override
    public void dismissLoading() {
        showContentView();
    }

    protected abstract void init();

    protected abstract int setLayout();

    protected abstract void setupAcitivtyComponent(AppComponent appComponent);

    public void onEmptyViewClick() {

    }

}
