package zkch.com.exerdemo.common.subscriber;

import android.content.Context;

import io.reactivex.disposables.Disposable;
import zkch.com.exerdemo.common.exception.BaseException;
import zkch.com.exerdemo.ui.BaseView;

public abstract class ProgressSubscriber<T> extends ErrorHandlerSubscriber<T> {
    private BaseView mBaseView;

    public ProgressSubscriber(Context context, BaseView mView) {
        super(context);
        this.mBaseView=mView;
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (e !=null){
            BaseException baseException = mErrorHandler.handlerError(e);
            if (e.getMessage().equals("permission disgranted")) {
                mBaseView.showError("授权失败");
            }else{
                mBaseView.showError(baseException.getDisplayMsg());
            }
        }
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

    public boolean isShowProgress(){
        return true;
    }


}