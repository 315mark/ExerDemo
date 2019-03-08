package zkch.com.exerdemo.common.subscriber;

import android.content.Context;

import io.reactivex.disposables.Disposable;
import zkch.com.exerdemo.cniaow.ui.BaseView;
import zkch.com.exerdemo.common.exception.BaseException;

public abstract class ProgressObserver<T> extends ErrorHandlerObserver<T> {
    private BaseView mBaseView;

    protected ProgressObserver(Context context, BaseView mView) {
        super(context);
        this.mBaseView = mView;
    }

    @Override
    public void onError(Throwable e) {

        e.printStackTrace();
        BaseException baseException = mErrorHandler.handlerError(e);
        mBaseView.showError(baseException.getDisplayMsg());
       /* super.onError(e);
        if (e != null) {
            BaseException baseException = mErrorHandler.handlerError(e);
            if (e.getMessage().equals("permission disgranted")) {
                mBaseView.showError("授权失败");
            } else {
                mBaseView.showError(baseException.getDisplayMsg());
            }
        }*/
    }

    @Override
    public void onSubscribe(Disposable d) {  //如何取消订阅
        if (isShowProgress()) {
            mBaseView.showLoading();
        }
    }

    @Override
    public void onComplete() {
        mBaseView.dismissLoading();
    }

    private boolean isShowProgress() {
        return true;
    }


}
