package zkch.com.exerdemo.common.subscriber;

import android.content.Context;

public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T> {
    public ProgressDialogSubscriber(Context context) {
        super(context);
    }

    /**
     * Provides the Observer with a new item to observe.
     * <p>
     * The {@link Observable} may call this method 0 or more times.
     * <p>
     * The {@code Observable} will not call this method again after it calls either {@link #onComplete} or
     * {@link #onError}.
     *
     * @param t the item emitted by the Observable
     */
    @Override
    public void onNext(T t) {

    }

    /**
     * Notifies the Observer that the {@link Observable} has finished sending push-based notifications.
     * <p>
     * The {@link Observable} will not call this method if it calls {@link #onError}.
     */
    @Override
    public void onComplete() {

    }
}
