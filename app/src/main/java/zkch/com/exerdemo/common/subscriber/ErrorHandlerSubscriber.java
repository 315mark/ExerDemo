package zkch.com.exerdemo.common.subscriber;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import io.reactivex.disposables.Disposable;
import zkch.com.exerdemo.common.exception.BaseException;
import zkch.com.exerdemo.common.rx.RxErrorHandler;
import zkch.com.exerdemo.ui.SimpleActivity;

public abstract class ErrorHandlerSubscriber<T> extends DefaultSunscriber<T> {

    protected RxErrorHandler mErrorHandler=null;
    protected Context context;

    public ErrorHandlerSubscriber(Context context) {
        this.context = context;
        mErrorHandler =new RxErrorHandler(context);
    }

    @Override
    public void onError(Throwable e) {
        BaseException Exception =mErrorHandler.handlerError(e);
        if (Exception == null){
            e.printStackTrace();
            Log.d("APPSTORE", "onError: " + e.getMessage());
        }else{
            mErrorHandler.showErrorMsg(Exception);
            if (Exception.getCode() == BaseException.ERROR_TOKEN) {
                toLogin();
            }
        }
    }

    private void toLogin(){
        Intent intent = new Intent(context, SimpleActivity.class);
        context.startActivity(intent);
    }

    /**
     * Provides the Observer with the means of cancelling (disposing) the
     * connection (channel) with the Observable in both
     * synchronous (from within {@link #onNext(Object)}) and asynchronous manner.
     *
     * @param d the Disposable instance whose {@link Disposable#dispose()} can
     *          be called anytime to cancel the connection
     * @since 2.0
     */
    @Override
    public void onSubscribe(Disposable d) {
        //   d.dispose();  取消订阅
        // onSubscribe 是2.x新添加的方法，在发射数据前被调用，相当于1.x的onStart方法
        //2， 参数为  Subscription ，Subscription 可用于向上游请求发射多少个元素，也可用于取笑请求
        //3,  必须要调用Subscription 的request来请求发射数据，不然上游是不会发射数据的。
    }
}
