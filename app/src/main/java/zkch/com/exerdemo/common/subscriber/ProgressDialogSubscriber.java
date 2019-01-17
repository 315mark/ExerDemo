package zkch.com.exerdemo.common.subscriber;

import android.content.Context;

public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T> {


    public ProgressDialogSubscriber(Context context) {
        super(context);
    }


    @Override
    public void onNext(T t) {

    }

    @Override
    public void onComplete() {

    }
}
