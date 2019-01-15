package zkch.com.exerdemo.common.subscriber;


import io.reactivex.Observer;
/**
 *   Rxjava1 Subscriber<T> 替换成Rxjava2 Observer<T> 订阅者
 *     Subscriber取消订阅 改成 onSubscribe(@NonNull Disposable d) 方法
 * @param <T>
 */
public abstract class DefaultSunscriber<T> implements Observer<T> {
}
