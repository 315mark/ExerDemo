package zkch.com.exerdemo.common.subscriber;

import android.content.Context;

public abstract class ProgressDialogObserver<T> extends ErrorHandlerObserver<T> {


    public ProgressDialogObserver(Context context) {
        super(context);
    }


    @Override
    public void onNext(T t) {

    }

    @Override
    public void onComplete() {

    }
}
