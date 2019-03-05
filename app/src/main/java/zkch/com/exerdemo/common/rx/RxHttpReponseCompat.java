package zkch.com.exerdemo.common.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.common.exception.ApiException;

/**
 * RXHttp 兼容处理类
 */
public class RxHttpReponseCompat {

    /* Observable.Transformer*/
    public static <T> ObservableTransformer<BaseBean<T>, T> compatResult() {

        return upstream -> (ObservableSource<T>) upstream.flatMap((Function<BaseBean<T>, ObservableSource<?>>) tBaseBean -> {
            if (tBaseBean.success()) {
                return Observable.create((ObservableOnSubscribe<T>) emitter -> {
                    emitter.onNext(tBaseBean.getData());
                    emitter.onComplete();
                });
            } else {
                return Observable.error(new ApiException(tBaseBean.getStatus(), tBaseBean.getMessage()));
            }

        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }
}
