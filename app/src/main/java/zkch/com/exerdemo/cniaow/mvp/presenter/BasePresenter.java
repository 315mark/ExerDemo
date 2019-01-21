package zkch.com.exerdemo.cniaow.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import zkch.com.exerdemo.cniaow.ui.BaseView;

public class BasePresenter<M, V extends BaseView> {

    protected M mModel;
    protected V mView;
    protected Context mContext;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
        initContext();
    }

    /**
     * Fragment 获取Activity的Context
     * 调用方并不能使用App级别的Context
     */
    private void initContext() {
        if (mView instanceof Fragment) {
            mContext = ((Fragment) mView).getActivity();
        } else {
            mContext = (Activity) mView;
        }
    }

}
