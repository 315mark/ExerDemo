package zkch.com.exerdemo.common.subscriber;

import android.content.Context;

import io.reactivex.disposables.Disposable;
import zkch.com.exerdemo.common.utils.ProgressDialogHandler;

public abstract class ProgressDialogObserver<T> extends ErrorHandlerObserver<T> implements ProgressDialogHandler.OnProgressCanCealableListener {

    private ProgressDialogHandler mHandler;
    private Disposable mDisposable;


    public ProgressDialogObserver(Context context) {
        super(context);
        mHandler = new ProgressDialogHandler(context);
    }

    protected boolean isShowProgressDialog() {
        return true;
    }

    @Override
    public void onCanCelProgress() {
        mDisposable.dispose();
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
        mDisposable = d;
        if (isShowProgressDialog()) {
            mHandler.showProgressDialog();
        }
    }

    @Override
    public void onComplete() {
        if (isShowProgressDialog()) {
            mHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (isShowProgressDialog()) {
            mHandler.dismissProgressDialog();
        }

    }
}
